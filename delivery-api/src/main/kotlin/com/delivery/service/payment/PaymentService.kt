package com.delivery.service.payment

import com.delivery.domain.payment.PaymentMethod
import com.delivery.domain.payment.PaymentStatus
import com.delivery.exception.NotFoundOrderException
import com.delivery.external.pg.PgAdapter
import com.delivery.external.pg.dto.PgPayRequest
import com.delivery.repository.order.OrderRepository
import com.delivery.repository.payment.Payment
import com.delivery.repository.payment.PaymentRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class PaymentService(
    private val orderRepository: OrderRepository,
    private val paymentRepository: PaymentRepository,
    private val pgAdapter: PgAdapter,
) {

    @Value("\${payment.pg-id}")
    val pgId: Long = 0L

    @Value("\${server.role-name}")
    lateinit var roleName: String

    fun pay(customerId: Long, orderId: Long, paymentMethod: PaymentMethod, payAmount: BigDecimal): Payment {
        val orderOptional = orderRepository.findById(orderId)
        if (orderOptional.isEmpty) {
            throw NotFoundOrderException("주문 정보를 찾을 수 없습니다. $orderId")
        }
        val order = orderOptional.get()

        val pgPayResponse = pgAdapter.pay(
            pgPayRequest = PgPayRequest(
                customerId = customerId,
                payAmount = order.totalAmount
            )
        )

        val payment = Payment(
            paymentMethod = paymentMethod,
            pgId = pgId,
            pgPaymentId = pgPayResponse.pgPaymentId,
            payAmount = payAmount,
            paymentStatus = PaymentStatus.COMPLETE,
            customerId = customerId,
            storeId = order.storeId,
            orderId = order.orderId,
        )
        payment.createdBy = roleName
        payment.updatedBy = roleName

        return paymentRepository.save(payment)
    }
}