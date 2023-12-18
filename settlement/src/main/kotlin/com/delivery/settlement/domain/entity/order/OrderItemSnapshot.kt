package com.delivery.settlement.domain.entity.order

import com.delivery.settlement.domain.entity.Product
import com.delivery.settlement.domain.entity.Seller
import com.delivery.settlement.domain.enums.TaxType
import com.delivery.settlement.domain.enums.TaxTypeConverter
import jakarta.persistence.*
import org.springframework.boot.context.properties.bind.DefaultValue
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class OrderItemSnapshot(
        @Id @Column(name = "order_item_snapshot_no") val id: Long,

        val createdAt: ZonedDateTime? = ZonedDateTime.now(),
        val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
        val deletedAt: ZonedDateTime? = null,

        val productNo: Long,
        val sellerNo: Long,

        val sellPrice: BigDecimal? = BigDecimal.ZERO,
        val supplyPrice: BigDecimal? = BigDecimal.ZERO,
        val promotionAmount: BigDecimal? = BigDecimal.ZERO,
        val defaultDeliveryAmount: BigDecimal? = BigDecimal.valueOf(3000),
        val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,

        val itemCategory: Int? = 0, //TODO : Enum으로 변경
        val taxRate: Int? = 3,

        @Convert(converter = TaxTypeConverter::class)
        val taxType: TaxType? = TaxType.TAX,

        @ManyToOne
        @JoinColumn(name = "seller_no", referencedColumnName = "id", insertable = false, updatable = false)
        val seller: Seller,

        @ManyToOne
        @JoinColumn(name = "product_no", referencedColumnName = "id", insertable = false, updatable = false)
        val product: Product,
)
