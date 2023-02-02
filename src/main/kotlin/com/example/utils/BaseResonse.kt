package com.example.utils

import io.ktor.http.*

sealed class BaseResonse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null
    ) : BaseResonse<T>()

    data class ErrorResponse<T>(
        val exception: T? = null,
        val message: String? = null
    ) : BaseResonse<T>()


}