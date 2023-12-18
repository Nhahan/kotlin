package com.delivery.service.customer.signup

import com.delivery.domain.customer.CustomerSignup
import com.delivery.exception.AlreadySignupCustomerException
import com.delivery.repository.customer.Customer
import com.delivery.repository.customer.CustomerRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CustomerSignupService(
    val customerRepository: CustomerRepository
) {
    @Value("\${server.role-name}")
    private lateinit var roleName: String

    fun signup(customerSignup: CustomerSignup): Customer {
        val customerOptional = customerRepository.findByEmail(customerSignup.email)
        if (customerOptional.isPresent) {
            throw AlreadySignupCustomerException("이미 가입된 회원입니다. ${customerSignup.email}")
        }

        val customer = Customer(
            name = customerSignup.name,
            email = customerSignup.email,
            password = customerSignup.password,
            address = customerSignup.address,
            phone = customerSignup.phone,
        )
        customer.createdBy = roleName
        customer.updatedBy = roleName
        return customerRepository.save(customer)
    }
}