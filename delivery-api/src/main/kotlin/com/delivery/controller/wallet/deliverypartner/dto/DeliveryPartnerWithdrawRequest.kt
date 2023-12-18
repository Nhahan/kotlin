package com.delivery.controller.wallet.deliverypartner.dto

import java.math.BigDecimal

data class DeliveryPartnerWithdrawRequest(
    val deliveryPartnerId: Long,
    val deliveryPartnerWalletId: Long,
    val amount: BigDecimal,
)
