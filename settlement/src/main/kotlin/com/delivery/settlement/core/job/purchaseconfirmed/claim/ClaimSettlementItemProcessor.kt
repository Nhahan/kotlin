package com.delivery.settlement.core.job.purchaseconfirmed.claim

import com.delivery.settlement.domain.collection.NegativeDailySettlementCollection
import com.delivery.settlement.domain.entity.claim.ClaimItem
import com.delivery.settlement.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class ClaimSettlementItemProcessor: ItemProcessor<ClaimItem, SettlementDaily> {

    override fun process(item: ClaimItem): SettlementDaily {
        return NegativeDailySettlementCollection(item).getSettlementDaily()
    }
}