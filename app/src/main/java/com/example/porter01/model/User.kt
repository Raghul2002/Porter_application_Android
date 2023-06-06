package com.example.porter01.model

data class User(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val emailId: String,
    val phoneNo: String,
    val id: Int? =0
)
