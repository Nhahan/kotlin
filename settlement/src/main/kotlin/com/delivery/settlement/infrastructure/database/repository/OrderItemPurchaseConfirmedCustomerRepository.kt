package com.delivery.settlement.infrastructure.database.repository

import com.delivery.settlement.domain.criteria.SearchForPurchaseConfirmedCriteria
import com.delivery.settlement.domain.entity.order.OrderItem

interface OrderItemPurchaseConfirmedCustomerRepository {
    fun getOrderItemForPurchaseConfirmedList(criteria: SearchForPurchaseConfirmedCriteria): List<OrderItem>
    fun getOrderItemForPurchaseConfirmedCount(criteria: SearchForPurchaseConfirmedCriteria): Int
}
