package com.example.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID


@Entity
@Table(
    name = "categories",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"])]
)
data class Category (
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    val name: String,

    val description: String? = null,

    val createdAt: Instant = Instant.now()
)