package com.example.composeapp.model.login

data class LoginResponse(
    val message: String,
    val userData: List<UserData>
)

data class UserData(
    val user_id: Int,
    val email_id: String,
    val name: String,
    val auth_key: String
)
