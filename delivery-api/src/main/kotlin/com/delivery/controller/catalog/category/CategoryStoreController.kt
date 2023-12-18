package com.delivery.controller.catalog.category

import com.delivery.controller.catalog.category.dto.CategoryStoreDTO
import com.delivery.controller.catalog.category.dto.CategoryStoreResponse
import com.delivery.service.store.StoreService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryStoreController(
    private val storeService: StoreService
) {
    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }
    @GetMapping("/apis/categories/stores")
    fun list(@RequestParam categoryId: Long, @RequestParam reviewGradeFilterValue: Int?) : ResponseEntity<CategoryStoreResponse> {
        logger.info { ">>> 카테고리 목록 조회, categoryId: $categoryId, reviewGradeFilterValue: $reviewGradeFilterValue" }
        return if (reviewGradeFilterValue == null) {
            val categoryStores = storeService.findByCategoryId(categoryId)
            val categoryStoreDTOS = categoryStores.map { CategoryStoreDTO.of(it) }
            ResponseEntity.ok(
                CategoryStoreResponse(categoryStores = categoryStoreDTOS)
            )
        } else {
            val categoryStores = storeService.findByCategoryIdAndReviewGrade(categoryId, reviewGradeFilterValue)
            val categoryStoreDTOS = categoryStores.map { CategoryStoreDTO.of(it) }
            ResponseEntity.ok(
                CategoryStoreResponse(categoryStores = categoryStoreDTOS)
            )
        }
    }
}