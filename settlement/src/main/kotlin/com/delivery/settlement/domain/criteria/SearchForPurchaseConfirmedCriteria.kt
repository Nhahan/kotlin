package com.delivery.settlement.domain.criteria

import java.time.LocalDate

data class SearchForPurchaseConfirmedCriteria(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val offset: Int,
        val limit: Int,
)
