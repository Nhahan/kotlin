package com.delivery.controller.auth.dto

data class AuthenticationRequest(
    val email: String,
    val password: String,
)