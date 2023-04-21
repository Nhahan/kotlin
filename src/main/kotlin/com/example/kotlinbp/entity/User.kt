package com.example.kotlinbp.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class User (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val email: String
) {
    constructor() : this(null, "")
}