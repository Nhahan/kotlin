package com.delivery.settlement.core.job.purchaseconfirmed.delivery

import com.delivery.settlement.domain.entity.order.OrderItem
import com.delivery.settlement.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.transaction.annotation.Transactional
import org.springframework.batch.item.ItemWriter
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
@Transactional
class PurchaseConfirmedWriter(
        private val orderItemRepository: OrderItemRepository
): ItemWriter<OrderItem> {
    override fun write(@NonNull chunk: Chunk<out OrderItem>) {
        println(chunk.items)
        for (item in chunk.items) {
            //TODO Hidden Task : item PurchaseConfirmedAt 업데이트하는 작업을 넣어줘야 한다.
            val newItem = item.copy(id = item.id, purchaseConfirmedAt = ZonedDateTime.now())
            //OrderItem을 저장
            orderItemRepository.save(newItem)
        }
    }
}
