package com.example.fcboard.controller.dto

data class PostUpdateRequest (
    val title: String? = null,
    val content: String? = null,
    val createdBy: String? = null
)
