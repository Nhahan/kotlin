package com.example.fcboard.service

import com.example.fcboard.common.annotation.CustomService
import com.example.fcboard.repository.PostRepository
import com.example.fcboard.service.dto.PostCreateRequestDto
import com.example.fcboard.service.dto.toEntity
import org.springframework.transaction.annotation.Transactional

@CustomService
class PostService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun createPost(requestDto: PostCreateRequestDto): Long? {
        return postRepository.save(requestDto.toEntity()).id
    }
}
