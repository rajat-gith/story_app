package com.example.repository

import com.example.service.CreateUserParams
import com.example.utils.BaseResonse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResonse<Any>
    suspend fun loginUser(email: String, password: String): BaseResonse<Any>
}