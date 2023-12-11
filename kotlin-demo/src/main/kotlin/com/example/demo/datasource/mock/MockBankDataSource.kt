package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    override fun getBanks(): Collection<Bank> {
        return listOf(
            Bank("1234", 3.14, 17),
            Bank("1010", 17.0, 0),
            Bank("5678", 0.0, 100),
        )
    }
}
