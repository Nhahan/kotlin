package com.delivery.settlement.core.job.purchaseconfirmed.daily

import com.delivery.settlement.domain.entity.settlement.SettlementDaily
import com.delivery.settlement.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Configuration

@Configuration
class DailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository
): ItemWriter<SettlementDaily> {

    override fun write(chunk: Chunk<out SettlementDaily>) {
        for (item in chunk.items) {
            settlementDailyRepository.save(item)
        }
    }
}