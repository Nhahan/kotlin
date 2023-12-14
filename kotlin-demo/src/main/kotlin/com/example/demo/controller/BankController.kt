package com.example.demo.controller

import com.example.demo.model.Bank
import com.example.demo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/api/banks")
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank {
        return service.addBank(bank)
    }

    @PatchMapping("/api/banks")
    fun updateBank(@RequestBody bank: Bank): Bank {
        return service.updateBank(bank)
    }

    @DeleteMapping("/api/banks/{accountNumber}")
    fun deleteBank(@PathVariable accountNumber: String): Unit {

    }
}