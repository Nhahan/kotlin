package com.delivery.repository.customer

import com.delivery.domain.customer.CustomerRole
import com.delivery.domain.customer.CustomerStatus
import com.delivery.repository.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers", schema = "food_delivery")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    val customerId: Long = 0L,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "address")
    val address: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    val customerStatus: CustomerStatus = CustomerStatus.ACTIVE,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    val customerRole: CustomerRole = CustomerRole.CUSTOMER

): BaseEntity()
