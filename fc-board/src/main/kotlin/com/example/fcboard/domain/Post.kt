package com.example.fcboard.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post(
    title: String,
    content: String,
    createdBy: String
): BaseEntity(createdBy) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var title: String = title
        private set
    var content: String = content
        private set
}
