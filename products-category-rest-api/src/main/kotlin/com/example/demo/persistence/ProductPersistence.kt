package com.example.demo.persistence

import com.example.demo.model.Product
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface ProductPersistence: JpaRepository<Product, UUID> {
    @EntityGraph(attributePaths = ["category"])
    override fun findAll(): List<Product>
    @EntityGraph(attributePaths = ["category"])
    override fun findById(id: UUID): Optional<Product>
    fun existsByCategoryId(categoryId: UUID) : Boolean
    fun findAllByCategoryId(categoryId: UUID) : List<Product>
    fun existsBySku(sku: String): Boolean
    fun findBySku(sku: String): Product?
}