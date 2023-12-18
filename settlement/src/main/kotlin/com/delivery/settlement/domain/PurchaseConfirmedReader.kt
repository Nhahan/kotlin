package com.delivery.settlement.domain

import com.delivery.settlement.domain.criteria.SearchForPurchaseConfirmedCriteria
import com.delivery.settlement.domain.entity.order.OrderItem

/**
 * List, Count조회를 위한 Reader Interface
 */
interface PurchaseConfirmedReader {
    fun getPurchaseConfirmedItemList(criteria: SearchForPurchaseConfirmedCriteria): List<OrderItem>
    fun getPurchaseConfirmedItemCount(criteria: SearchForPurchaseConfirmedCriteria): Int
}
