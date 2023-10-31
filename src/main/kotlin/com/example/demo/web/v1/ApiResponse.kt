package com.example.demo.web.v1

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
    }
}
