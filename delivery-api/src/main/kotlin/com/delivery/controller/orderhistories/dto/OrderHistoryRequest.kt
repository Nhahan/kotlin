package com.delivery.controller.orderhistories.dto

import com.delivery.domain.order.OrderStatus


data class OrderHistoryRequest(
    val customerId: Long,
    val orderStatus: OrderStatus,
)