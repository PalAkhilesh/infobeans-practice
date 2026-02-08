package com.example.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "products",
    uniqueConstraints = [UniqueConstraint(columnNames = ["sku"])]
)
data class Product(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false, unique = true)
    var sku:String,
    @Column(nullable = false)
    var price: Double,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,
    val createdAt: Instant = Instant.now()
)
