package com.delivery.service.category

import com.delivery.repository.category.Category
import com.delivery.repository.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun list(): List<Category> {
        return categoryRepository.findAllAvailableCategory()
    }
}