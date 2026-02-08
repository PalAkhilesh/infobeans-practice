package com.example.demo.persistence

import com.example.demo.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryPersistence: JpaRepository<Category, UUID> {
    fun findByName(name: String): Category?
}