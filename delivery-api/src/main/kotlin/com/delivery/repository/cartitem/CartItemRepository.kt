package com.delivery.repository.cartitem

import com.delivery.domain.cart.CartMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartItemRepository: JpaRepository<CartItem, Long> {

    fun findCartItemByCartIdAndMenuIdAndStoreIdAndIsDeletedIsFalse(cartId: Long, menuId: Long, storeId: Long): Optional<CartItem>

    fun findCartItemByCartIdAndMenuIdAndIsDeletedIsFalse(cartId: Long, menuId: Long): Optional<CartItem>

    @Query(
        value = """
            SELECT new com.delivery.domain.cart.CartMenu(
                ci.cartId, 
                ci.cartItemId, 
                ci.menuId, 
                m.menuName, 
                m.menuMainImageUrl, 
                m.price, 
            ci.quantity) 
            FROM CartItem ci
            JOIN Menu m ON m.menuId = ci.menuId
            WHERE ci.cartId = :cartId AND ci.isDeleted = false
        """
    )
    fun findAllByCartId(@Param("cartId") cartId: Long): List<CartMenu>

    fun findAllByCartIdAndMenuIdIn(cartId: Long, menuIds: List<Long>): List<CartItem>
}