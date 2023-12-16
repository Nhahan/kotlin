package com.example.fcboard.domain

import com.example.fcboard.service.dto.PostUpdateRequestDto
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class Post(
    title: String,
    content: String,
    createdBy: String
): BaseEntity(createdBy) {
    var title: String = title
        private set
    var content: String = content
        private set

    fun update(postUpdateRequestDto: PostUpdateRequestDto) {
        title = postUpdateRequestDto.title
        content = postUpdateRequestDto.content
        updatedAt = LocalDateTime.now()
    }
}
