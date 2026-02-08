package com.example.demo.dto

import java.time.Instant
import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val title: String,
    val sku: String,
    val price: Double,
    val category: CategoryResponse,
    val createdAt: Instant
)