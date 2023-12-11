package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankServiceTest {

    private val bankDataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // when
        val banks = bankService.getBanks()

        // then
        verify(exactly = 1) { bankDataSource.getBanks() }
    }
}