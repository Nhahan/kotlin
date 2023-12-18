package com.delivery.domain.order

import com.delivery.domain.orderitem.OrderItemMenu
import com.delivery.repository.orderdiscount.OrderDiscountItem

data class OrderDetail(
    val orderId: Long,
    val customerId: Long,
    val storeId: Long,
    val orderItems: List<OrderItemMenu>,
    val orderDiscountItem: OrderDiscountItem?,
)
