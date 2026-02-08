package com.example.demo.mapper

import com.example.demo.dto.ProductRequest
import com.example.demo.dto.ProductResponse
import com.example.demo.model.Product
import com.example.demo.model.Category

fun ProductRequest.toEntity(category: Category) = Product(
    title = this.title,
    sku = this.sku,
    price = this.price,
    category = category
)

fun Product.toResponseDto(): ProductResponse = ProductResponse(
    id = this.id,
    title = this.title,
    sku = this.sku,
    price = this.price,
    category = this.category.toResponseDto(),
    createdAt = this.createdAt
)

fun List<Product>.toResponseDtos(): List<ProductResponse> = map { it.toResponseDto() }
