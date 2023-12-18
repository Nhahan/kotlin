package com.delivery.controller.wallet.store.dto

import java.math.BigDecimal

data class StoreWalletWithdrawResponse(
    val storeId: Long,
    val storeWalletId: Long,
    val amount: BigDecimal,
)