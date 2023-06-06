package com.example.porter01.repository

import com.example.porter01.model.User

interface UserRepository {
    fun insertUser(user : User)
    fun getUser(username: String, password: String): User?
}