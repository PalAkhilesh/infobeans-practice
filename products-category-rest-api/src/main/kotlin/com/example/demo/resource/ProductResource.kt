package com.example.demo.resource

import com.example.demo.mapper.toResponseDto
import com.example.demo.mapper.toResponseDtos
import com.example.demo.dto.ProductRequest
import com.example.demo.dto.ProductResponse
import com.example.demo.service.ProductService
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
@RequestMapping("/api/v1/products")
class ProductResource(
    private val productService: ProductService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest) =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.create(request).toResponseDto())
    
    @GetMapping
    fun getAll(): List<ProductResponse> = productService.getAll().toResponseDtos()
    
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<ProductResponse> = 
        ResponseEntity.ok(productService.getById(id).toResponseDto())
    
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID,
                        @Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> =
                    ResponseEntity.ok(productService.updateById(id, request).toResponseDto())
    
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void>{
        productService.deleteById(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}