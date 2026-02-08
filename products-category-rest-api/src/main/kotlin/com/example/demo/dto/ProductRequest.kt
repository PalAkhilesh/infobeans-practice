package com.example.demo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.util.UUID

data class ProductRequest (
    @field:NotBlank(message = "Product title required")
    val title: String,
    @field:NotBlank(message = "Product SKU required")
    val sku: String,
    @field:Positive(message = "Product price should be positive")
    val price: Double,
    val categoryId: UUID
)