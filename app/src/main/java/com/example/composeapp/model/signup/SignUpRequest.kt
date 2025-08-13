package com.example.composeapp.model.signup

data class SignUpRequest(
    val name: String,
    val email_id: String,
    val password: String,
    val time_offset: String
)
