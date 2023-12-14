package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> {
        return bankDataSource.getBanks()
    }

    fun getBank(accountNumber: String): Bank {
        return bankDataSource.getBank(accountNumber)
    }

    fun addBank(bank: Bank): Bank {
        return bankDataSource.addBank(bank)
    }
}