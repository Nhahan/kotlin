package com.delivery.controller.checkout.dto

data class CheckoutRequest(
    val customerId: Long,
    val discountId: Long,
)