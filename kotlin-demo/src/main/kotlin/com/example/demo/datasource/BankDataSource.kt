package com.example.demo.datasource

import com.example.demo.model.Bank

interface BankDataSource {

    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String): Bank {
        return getBanks().firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }
}