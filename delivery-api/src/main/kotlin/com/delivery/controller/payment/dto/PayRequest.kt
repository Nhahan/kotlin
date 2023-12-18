package com.delivery.controller.payment.dto

import com.delivery.domain.payment.PaymentMethod

data class PayRequest(
    val checkoutId: Long,
    val customerId: Long,
    val paymentMethod: PaymentMethod,
)