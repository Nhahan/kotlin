package com.delivery.controller.order.dto

import com.delivery.domain.order.OrderDetail
import com.delivery.domain.orderdiscount.OrderDiscountItemDTO

data class OrderDetailDTO(
    val orderId: Long,
    val customerId: Long,
    val storeId: Long,
    val orderItemDTOs: List<OrderItemDTO>,
    val orderDiscountItemDTO: OrderDiscountItemDTO?
) {
    companion object {
        fun from(orderDetail: OrderDetail): OrderDetailDTO {
            val orderItemDTOS = orderDetail.orderItems.map { OrderItemDTO.from(it) }
            val orderDiscountItemDTO = OrderDiscountItemDTO.from(orderDetail.orderDiscountItem)
            return OrderDetailDTO(
                orderId = orderDetail.orderId,
                customerId = orderDetail.customerId,
                storeId = orderDetail.storeId,
                orderItemDTOs = orderItemDTOS,
                orderDiscountItemDTO = orderDiscountItemDTO
            )
        }
    }
}
