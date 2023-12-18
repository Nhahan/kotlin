package com.delivery.service.customer

import com.delivery.repository.customer.Customer
import com.delivery.repository.customer.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun createCustomer(customer: Customer): Optional<Customer> {
        return customerRepository.findByEmail(customer.email)
    }
    fun findByCustomerId(customerId: Long): Optional<Customer> =
        customerRepository.findById(customerId)

    fun findAll(): List<Customer> =
        customerRepository.findAll()
            .toList()

    fun deleteByCustomerId(customerId: Long): Customer {
        val customerOptional = customerRepository.findById(customerId)
        val customer = customerOptional.get()
        customer.isDeleted = true
        return customerRepository.save(customer)
    }

}