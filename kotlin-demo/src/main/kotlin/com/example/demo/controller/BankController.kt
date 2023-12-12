package com.example.demo.controller

import com.example.demo.model.Bank
import com.example.demo.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BankController(private val service: BankService) {

    @GetMapping("/api/banks")
    fun getBanks(): Collection<Bank> {
        return service.getBanks()
    }

    @GetMapping("/api/banks/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): Bank {
        return service.getBank(accountNumber)
    }
}