package com.delivery.settlement.core.job.purchaseconfirmed.daily

import com.delivery.settlement.domain.collection.CommissionAmountCalculator
import com.delivery.settlement.domain.collection.PgSalesAmountCalculator
import com.delivery.settlement.domain.collection.PositiveDailySettlementCollection
import com.delivery.settlement.domain.collection.TaxCalculator
import com.delivery.settlement.domain.entity.order.OrderItem
import com.delivery.settlement.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDate

class DailySettlementItemProcessor: ItemProcessor<OrderItem, SettlementDaily> {

    /**
     * + 정산금액을 만들기
     */
    override fun process(item: OrderItem): SettlementDaily {
        val positiveDailySettlementCollection = PositiveDailySettlementCollection(item)

        return positiveDailySettlementCollection.getSettlementDaily()
    }
}