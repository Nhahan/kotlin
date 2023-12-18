package com.delivery.controller.payment

import com.delivery.controller.order.dto.OrderRequest
import com.delivery.controller.payment.dto.PayRequest
import com.delivery.controller.payment.dto.PayResponse
import com.delivery.service.order.OrderService
import com.delivery.service.payment.PaymentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "PaymentController", description = "결제 처리 컨트롤러")
@RestController
class PaymentController(
    private val paymentService: PaymentService,
    private val orderService: OrderService,
) {

    @PostMapping("/apis/payments")
    @Operation(
        summary = "주문서 결제하는 API", description = "주문서 결제를 처리하는 API"
    )
    fun pay(@RequestBody payRequest: PayRequest): PayResponse {
        val orderRequest = OrderRequest(
            checkoutId = payRequest.checkoutId,
            customerId = payRequest.customerId,
        )
        val order = orderService.order(orderRequest = orderRequest)
        val payResult = paymentService.pay(
            customerId = payRequest.customerId,
            orderId = order.orderId,
            paymentMethod = payRequest.paymentMethod,
            payAmount = order.totalAmount,
        )

        return PayResponse(
            orderId = payResult.orderId,
            pgId = payResult.pgId,
            pgPaymentId = payResult.pgPaymentId,
            payAmount = payResult.payAmount,
            customerId = payResult.customerId,
        )
    }
}