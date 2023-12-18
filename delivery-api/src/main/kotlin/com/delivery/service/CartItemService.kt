package com.delivery.service

import com.delivery.domain.cart.CartMenu
import com.delivery.repository.cartitem.CartItemRepository
import org.springframework.stereotype.Service

@Service
class CartItemService(
    private val cartItemRepository: CartItemRepository
) {
    fun findAllByCartId(cartId: Long): List<CartMenu> {
        return cartItemRepository.findAllByCartId(cartId)
    }

    fun remove(cartId: Long, orderedMenuIds: List<Long>) {
        val cartItems = cartItemRepository.findAllByCartIdAndMenuIdIn(cartId = cartId, menuIds = orderedMenuIds)
        cartItems.forEach {
            it.isDeleted = true
            it.quantity = 0
        }
        cartItemRepository.saveAll(cartItems)
    }
}