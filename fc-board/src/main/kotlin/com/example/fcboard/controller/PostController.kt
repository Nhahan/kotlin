package com.example.fcboard.controller

import com.example.fcboard.controller.dto.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
class PostController {

    @PostMapping("/posts")
    fun createPost(
        @RequestBody postCreateRequest: PostCreateRequest
    ): Long {
        return 1L
    }

    @GetMapping("/posts/{postId}")
    fun getPost(
        @PathVariable postId: Long
    ): PostDetailResponse {
        return PostDetailResponse(
            id = postId,
            title = "제목",
            content = "내용",
            createdBy = "작성자",
            createdAt = "작성일",
            updatedAt = "수정일"
        )
    }

    @GetMapping("/posts")
    fun getPosts(
        pageable: Pageable,
        @ModelAttribute postSearchRequest: PostSearchRequest
    ): Page<PostSummaryResponse> {
        return Page.empty()
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
