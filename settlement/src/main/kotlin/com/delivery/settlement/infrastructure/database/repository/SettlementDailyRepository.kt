package com.delivery.settlement.infrastructure.database.repository

import com.delivery.settlement.domain.entity.settlement.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository: JpaRepository<SettlementDaily, Long> {
}