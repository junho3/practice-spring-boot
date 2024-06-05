package com.example.demo.web.v1

import com.example.demo.common.exception.ErrorCode

data class ApiResponse<E>(
    val success: Boolean,
    val code: String? = null,
    val message: String? = null,
    val data: E?,
) {
    companion object {
        fun <E> success(result: E): ApiResponse<E> {
            return ApiResponse(
                success = true,
                data = result,
            )
        }

        fun <E> error(errorCode: ErrorCode): ApiResponse<E> {
            return ApiResponse(
                success = false,
                code = errorCode.code,
                message = errorCode.message,
                data = null
            )
        }

        fun <E> error(errorCode: ErrorCode, message: String): ApiResponse<E> {
            return ApiResponse(
                success = false,
                code = errorCode.code,
                message = message,
                data = null
            )
        }
    }
}
