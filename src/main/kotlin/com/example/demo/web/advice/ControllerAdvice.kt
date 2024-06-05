package com.example.demo.web.advice

import com.example.demo.common.exception.ErrorCode
import com.example.demo.web.v1.ApiResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleRunTimeException(e: Exception): ApiResponse<Any> {
        logger.error("Exception : {}", e.message, e)
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR)
    }
}

val logger = KotlinLogging.logger {}
