package com.example.demo.service

import com.example.demo.mapper.toEntity
import com.example.demo.dto.CategoryRequest
import com.example.demo.exception.ConflictException
import com.example.demo.exception.NotFoundException
import com.example.demo.model.Category
import com.example.demo.persistence.CategoryPersistence
import com.example.demo.persistence.ProductPersistence
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(
    private val categoryPersistence: CategoryPersistence,
    private val productPersistence: ProductPersistence
) {

    fun create(category: CategoryRequest): Category {
        if(categoryPersistence.findByName(category.name) != null){
            throw ConflictException("Category already exists")
        }
        return categoryPersistence.save(category.toEntity())
    }

    fun getAll(): List<Category> = categoryPersistence.findAll()

    fun getById(id: UUID): Category = categoryPersistence.findById(id)
        .orElseThrow { NotFoundException("Category not found") }

    fun update(id: UUID, category: CategoryRequest): Category {
        val existing = getById(id)
        
        // Check if new name already exists and is different from current
        if (category.name != existing.name && categoryPersistence.findByName(category.name) != null) {
            throw ConflictException("Category name already exists")
        }
        
        return categoryPersistence.save(existing.copy(
            name = category.name,
            description = category.description
        ))
    }

    fun delete(id: UUID) {
        if(productPersistence.existsByCategoryId(id)){
            throw ConflictException("Cannot delete category with products")
        }
        categoryPersistence.delete(
            categoryPersistence.findById(id)
                .orElseThrow { NotFoundException("Category not found") }
        )
    }
}