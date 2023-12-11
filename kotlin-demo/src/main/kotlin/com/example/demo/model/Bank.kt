package com.example.demo.model

data class Bank(
    // var 일 경우 getter, setter 자동 생성
    // val 일 경우 getter 자동 생성
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)
