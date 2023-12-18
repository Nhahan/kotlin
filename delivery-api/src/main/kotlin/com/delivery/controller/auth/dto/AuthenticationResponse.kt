package com.delivery.controller.auth.dto

data class AuthenticationResponse(
    val customerId: Long,
    val email: String,
    val accessToken: String,
    val refreshToken: String,
)
