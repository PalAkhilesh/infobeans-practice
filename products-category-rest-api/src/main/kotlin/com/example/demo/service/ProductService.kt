package com.example.demo.service

import com.example.demo.mapper.toEntity
import com.example.demo.dto.ProductRequest
import com.example.demo.exception.NotFoundException
import com.example.demo.exception.ConflictException
import com.example.demo.model.Product
import com.example.demo.persistence.CategoryPersistence
import com.example.demo.persistence.ProductPersistence
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val productPersistence: ProductPersistence,
    private val categoryPersistence: CategoryPersistence
) {

    fun create(request: ProductRequest) : Product {
        val category = categoryPersistence.findById(request.categoryId)
            .orElseThrow { NotFoundException("Category not found") }
        if (productPersistence.existsBySku(request.sku)) {
            throw ConflictException("Product SKU already exists")
        }
        return productPersistence.save(request.toEntity(category))
    }
    fun getAll(): List<Product> = productPersistence.findAll()

    fun getById(id: UUID) =
        productPersistence.findById(id)
            .orElseThrow { NotFoundException("Product not found") }

    fun updateById(id: UUID, request: ProductRequest): Product{
        val product = getById(id)
        val category = categoryPersistence.findById(request.categoryId)
            .orElseThrow { NotFoundException("Category not found") }
        if (request.sku != product.sku && productPersistence.existsBySku(request.sku)) {
            throw ConflictException("Product SKU already exists")
        }
        return productPersistence.save(product.copy(
            title = request.title,
            sku = request.sku,
            price = request.price,
            category = category
        ))
    }

    fun deleteById(id: UUID){
        val product = getById(id)
        productPersistence.delete(product)
    }
}