package com.example.demo.mapper

import com.example.demo.dto.CategoryRequest
import com.example.demo.dto.CategoryResponse
import com.example.demo.model.Category

// Extension functions for clean, idiomatic Kotlin mapping

fun CategoryRequest.toEntity(): Category = Category(
    name = this.name,
    description = this.description
)

fun Category.toResponseDto(): CategoryResponse = CategoryResponse(
    id = this.id,
    name = this.name,
    description = this.description,
    createdAt = this.createdAt
)

fun List<Category>.toResponseDtos(): List<CategoryResponse> = map { it.toResponseDto() }
