package com.delivery.settlement.infrastructure.database.repository

import com.delivery.settlement.domain.entity.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.time.ZonedDateTime

interface OrderItemRepository : JpaRepository<OrderItem, Long>, OrderItemPurchaseConfirmedCustomerRepository {

    fun findByShippedCompleteAtBetween(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): List<OrderItem>
}
