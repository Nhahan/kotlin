package com.delivery.settlement.infrastructure.database.repository

import com.delivery.settlement.domain.criteria.SearchForPurchaseConfirmedCriteria
import com.delivery.settlement.domain.entity.order.OrderItem

class OrderItemPurchaseConfirmedCustomerRepositoryImpl: OrderItemPurchaseConfirmedCustomerRepository {

    override fun getOrderItemForPurchaseConfirmedList(criteria: SearchForPurchaseConfirmedCriteria): List<OrderItem> {
        TODO("Not yet implemented")
    }

    override fun getOrderItemForPurchaseConfirmedCount(criteria: SearchForPurchaseConfirmedCriteria): Int {
        TODO("Not yet implemented")
    }
}
