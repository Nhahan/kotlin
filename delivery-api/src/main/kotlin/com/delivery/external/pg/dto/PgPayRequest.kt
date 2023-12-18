package com.delivery.external.pg.dto

import java.math.BigDecimal

data class PgPayRequest(
    val customerId: Long,
    val payAmount: BigDecimal,
)
