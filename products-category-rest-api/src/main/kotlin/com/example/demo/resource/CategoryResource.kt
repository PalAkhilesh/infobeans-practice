package com.example.demo.resource

import com.example.demo.mapper.toResponseDto
import com.example.demo.mapper.toResponseDtos
import com.example.demo.dto.CategoryRequest
import com.example.demo.dto.CategoryResponse
import com.example.demo.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/categories")
class CategoryResource(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CategoryRequest) =
           ResponseEntity.status(HttpStatus.CREATED)
               .body(categoryService.create(request).toResponseDto())

    @GetMapping
    fun getAll(): List<CategoryResponse> = categoryService.getAll().toResponseDtos()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<CategoryResponse> =
        ResponseEntity.ok(categoryService.getById(id).toResponseDto())

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @Valid @RequestBody request: CategoryRequest): ResponseEntity<CategoryResponse> =
        ResponseEntity.ok(categoryService.update(id, request).toResponseDto())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        categoryService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}