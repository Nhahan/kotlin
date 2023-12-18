package com.delivery.service.search

import com.delivery.repository.store.Store
import com.delivery.repository.store.StoreRepository
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val storeRepository: StoreRepository,
) {
    fun search(keyword: String, reviewGrade: Int?): List<Store> {
        return if (reviewGrade == null) {
            this.storeRepository.findAllByStoreNameContains(keyword)
        } else {
            this.storeRepository.findAllByStoreNameContainsAndReviewGradeIsGreaterThanEqual(keyword, reviewGrade)
        }
    }
}