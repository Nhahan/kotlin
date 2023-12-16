package com.example.fcboard.service

import com.example.fcboard.common.annotation.CustomService
import com.example.fcboard.repository.PostRepository
import com.example.fcboard.service.dto.PostCreateRequestDto
import com.example.fcboard.service.dto.PostUpdateRequestDto
import com.example.fcboard.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@CustomService
class PostService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun createPost(requestDto: PostCreateRequestDto): Long? {
        return postRepository.save(requestDto.toEntity()).id
    }

    @Transactional
    fun updatePost(postId: Long, postUpdateRequestDto: PostUpdateRequestDto): Long {
        val post = postRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("해당 게시글이 없습니다. id=$postId")
        post.update(postUpdateRequestDto)
        return postId
    }

    @Transactional
    fun deletePost(postId: Long) {
        postRepository.deleteById(postId)
    }
}
