package com.example.demo.dto

import java.time.Instant
import java.util.UUID

data class CategoryResponse(val id: UUID, val name: String, val description: String?, val createdAt: Instant )