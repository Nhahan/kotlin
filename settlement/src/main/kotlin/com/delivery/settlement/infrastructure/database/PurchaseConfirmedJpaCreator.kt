package com.delivery.settlement.infrastructure.database

import com.delivery.settlement.domain.PurchaseConfirmedReader
import com.delivery.settlement.domain.criteria.SearchForPurchaseConfirmedCriteria
import com.delivery.settlement.domain.entity.order.OrderItem
import com.delivery.settlement.infrastructure.database.repository.OrderItemRepository
import org.springframework.stereotype.Service

@Service
class PurchaseConfirmedJpaCreator(private val repository: OrderItemRepository) :PurchaseConfirmedReader{
    override fun getPurchaseConfirmedItemList(criteria: SearchForPurchaseConfirmedCriteria): List<OrderItem> {
        return repository.getOrderItemForPurchaseConfirmedList(criteria)
    }

    override fun getPurchaseConfirmedItemCount(criteria: SearchForPurchaseConfirmedCriteria): Int {
        return repository.getOrderItemForPurchaseConfirmedCount(criteria)
    }
}
