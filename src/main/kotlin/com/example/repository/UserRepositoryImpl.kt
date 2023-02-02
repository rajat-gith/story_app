package com.example.repository

import com.example.secuity.JwtConfig
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.utils.BaseResonse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResonse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResonse.ErrorResponse("Email Already Exists")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResonse.SuccessResponse(data = user)
            } else {
                BaseResonse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResonse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}