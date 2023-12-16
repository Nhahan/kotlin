package com.example.fcboard.controller

import com.example.fcboard.controller.dto.PostCreateRequest
import com.example.fcboard.controller.dto.PostUpdateRequest
import org.springframework.web.bind.annotation.*

@RestController
class PostController {

    @PostMapping("/posts")
    fun createPost(
        @RequestBody postCreateRequest: PostCreateRequest
    ): Long {
        return 1L
    }

    @PutMapping("/posts/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody postUpdateRequest: PostUpdateRequest
    ): Long {
        return postId
    }

    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
        @RequestParam createdBy: String
    ): Long {
        return postId
    }
}
