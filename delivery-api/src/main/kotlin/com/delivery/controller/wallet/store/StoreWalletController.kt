package com.delivery.controller.wallet.store

import com.delivery.controller.wallet.store.dto.StoreWalletDepositRequest
import com.delivery.controller.wallet.store.dto.StoreWalletDepositResponse
import com.delivery.controller.wallet.store.dto.StoreWalletWithdrawRequest
import com.delivery.controller.wallet.store.dto.StoreWalletWithdrawResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "StoreWalletController", description = "상점 지갑 컨트롤러")
@RestController
class StoreWalletController {

    @PostMapping("/wallets/stores/{storeId}/deposit")
    fun deposit(
        @PathVariable storeId: Long,
        storeWalletDepositRequest: StoreWalletDepositRequest): StoreWalletDepositResponse {
        return StoreWalletDepositResponse(
            storeId = storeWalletDepositRequest.storeId,
            storeWalletId = storeWalletDepositRequest.storeWalletId,
            amount = storeWalletDepositRequest.amount,
        )
    }

    @PostMapping("/wallets/stores/{storeId}/withdraw")
    fun withdraw(
        @PathVariable storeId: Long,
        storeWalletWithdrawRequest: StoreWalletWithdrawRequest
    ): StoreWalletWithdrawResponse {
        return StoreWalletWithdrawResponse(
            storeId = storeWalletWithdrawRequest.storeId,
            storeWalletId = storeWalletWithdrawRequest.storeWalletId,
            amount = storeWalletWithdrawRequest.amount
        )
    }
}