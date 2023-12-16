package com.example.fcboard.service

import com.example.fcboard.repository.PostRepository
import com.example.fcboard.service.dto.PostCreateRequestDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class PostServiceTest(
    private val postService: PostService,
    private val postRepository: PostRepository
): BehaviorSpec({
    given("create post") {
        `when`("create post") {
            val postId = postService.createPost(
                PostCreateRequestDto(
                title = "title",
                content = "content",
                createdBy = "createdBy"
            )
            )
            then("create post") {
                postId?.shouldBeGreaterThan(0L)
                val post = postRepository.findByIdOrNull(postId)
                post shouldNotBe null
                post?.title shouldBe "title"
                post?.content shouldBe "content"
                post?.createdBy shouldBe "createdBy"
            }
        }
    }
})
