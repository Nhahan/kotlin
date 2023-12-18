package com.delivery.controller.order.dto

data class OrderRequest(
    val checkoutId: Long,
    val customerId: Long,
)