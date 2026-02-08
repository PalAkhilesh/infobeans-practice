package com.example.demo.dto

import jakarta.validation.constraints.NotBlank

data class CategoryRequest (
    @field: NotBlank(message = "Category name required")
    val name: String,
    val description: String
)