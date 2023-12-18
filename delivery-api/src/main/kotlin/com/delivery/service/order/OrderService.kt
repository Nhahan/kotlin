package com.delivery.service.order

import com.delivery.controller.order.dto.OrderRequest
import com.delivery.domain.order.OrderDetail
import com.delivery.domain.order.OrderUUIDGenerator
import com.delivery.exception.DuplicateOrderException
import com.delivery.exception.NotFoundCheckoutException
import com.delivery.exception.NotFoundOrderException
import com.delivery.repository.checkout.Checkout
import com.delivery.repository.checkout.CheckoutRepository
import com.delivery.repository.checkoutitem.CheckoutItem
import com.delivery.repository.checkoutitem.CheckoutItemRepository
import com.delivery.repository.order.Order
import com.delivery.repository.order.OrderRepository
import com.delivery.repository.orderdiscount.OrderDiscountItem
import com.delivery.repository.orderdiscount.OrderDiscountItemRepository
import com.delivery.repository.orderitem.OrderItem
import com.delivery.repository.orderitem.OrderItemRepository
import com.delivery.service.CartItemService
import com.delivery.service.cart.CartService
import com.delivery.service.discount.DiscountService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class OrderService(
    private val checkoutRepository: CheckoutRepository,
    private val checkoutItemRepository: CheckoutItemRepository,
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val discountService: DiscountService,
    private val orderDiscountItemRepository: OrderDiscountItemRepository,
    private val cartService: CartService,
    private val cartItemService: CartItemService,
) {
    @Value("\${server.role-name}")
    lateinit var roleName: String

    fun order(orderRequest: OrderRequest): Order {
        val checkoutOptional = checkoutRepository.findById(orderRequest.checkoutId)
        if (checkoutOptional.isEmpty) {
            throw NotFoundCheckoutException("체크아웃 정보를 찾을 수 없습니다. ${orderRequest.checkoutId}")
        }
        val checkout = checkoutOptional.get()
        validateDuplicatedOrder(orderRequest.checkoutId)
        val checkoutItems = checkoutItemRepository.findAllByCheckoutId(checkout.checkoutId)
        val menuPrices = checkoutItems.map { it.menuPrice.multiply(BigDecimal(it.menuQuantity)) }
        val orderAmount = menuPrices.sumOf { it }
        val maxDiscount = discountService.findDiscountBy(checkoutId = checkout.checkoutId)
        val discountValue = maxDiscount?.discountValue?.let { BigDecimal(it) } ?: BigDecimal(0)
        val totalAmount = orderAmount.minus(discountValue)

        // 주문 생성
        val createdOrder = createOrder(checkout, orderAmount, discountValue, totalAmount)

        // 주문 아이템 생성
        val orderItems = createOrderItems(checkoutItems, createdOrder)

        // 장바구니 아이템 삭제
        removeCartItems(orderRequest, orderItems)

        return createdOrder
    }

    private fun createOrder(
        checkout: Checkout,
        orderAmount: BigDecimal,
        discountValue: BigDecimal,
        totalAmount: BigDecimal
    ): Order {
        val orderUUID = OrderUUIDGenerator.gen()
        val order = Order(
            orderUUID = orderUUID.id,
            orderShortenId = orderUUID.shortenId,
            checkoutId = checkout.checkoutId,
            orderAmount = orderAmount,
            discountAmount = discountValue,
            deliveryFee = BigDecimal.ZERO,
            totalAmount = totalAmount,
            storeId = checkout.storeId,
            customerId = checkout.customerId,
        )
        order.createdBy = roleName
        order.updatedBy = roleName
        val createdOrder = orderRepository.save(order)
        return createdOrder
    }

    private fun createOrderItems(
        checkoutItems: List<CheckoutItem>,
        createdOrder: Order
    ): List<OrderItem> {
        val orderItems = checkoutItems.map {
            val orderItem = OrderItem(
                orderId = createdOrder.orderId,
                menuId = it.menuId,
                menuPrice = it.menuPrice,
                menuQuantity = it.menuQuantity,
            )
            orderItem.createdBy = roleName
            orderItem.updatedBy = roleName
            orderItem
        }
        orderItemRepository.saveAll(orderItems)
        return orderItems
    }

    private fun removeCartItems(
        orderRequest: OrderRequest,
        orderItems: List<OrderItem>
    ) {
        val cart = cartService.findByCustomerId(customerId = orderRequest.customerId)
        val orderedMenuIds = orderItems.map { it.menuId }.toList()
        cartItemService.remove(cartId = cart.cartId, orderedMenuIds = orderedMenuIds)
    }

    private fun validateDuplicatedOrder(checkoutId: Long) {
        val existsByCheckoutId = orderRepository.existsByCheckoutId(checkoutId)
        if (existsByCheckoutId) {
            throw DuplicateOrderException("이미 처리된 주문입니다. checkoutId: $checkoutId")
        }
    }

    fun detail(orderId: Long): OrderDetail {
        val orderOptional = orderRepository.findById(orderId)
        if (orderOptional.isEmpty) {
            throw NotFoundOrderException("요청한 주문서($orderId) 정보를 찾을 수 없습니다.")
        }

        val order = orderOptional.get()
        val orderItemMenus = orderItemRepository.findAllByOrderId(orderId = orderId)
        val orderDiscountItems = orderDiscountItemRepository.findAllByOrderId(orderId = orderId)
        val orderDiscountItem: OrderDiscountItem? = if (orderDiscountItems.isNotEmpty()) orderDiscountItems.first() else null

        return OrderDetail(
            orderId = orderId,
            customerId = order.customerId,
            storeId = order.storeId,
            orderItems = orderItemMenus,
            orderDiscountItem = orderDiscountItem
        )
    }
}