package com.delivery.service.menu

import com.delivery.domain.order.RecentOrderStore
import com.delivery.repository.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class FavoriteStoreService(
    private val orderRepository: OrderRepository,
) {
    fun list(customerId: Long?): List<RecentOrderStore> {
        return if (customerId == null) {
            this.orderRepository.findRecentOrderStore()
        } else {
            this.orderRepository.findRecentOrderStoreByCustomerId(customerId = customerId)
        }
    }
}