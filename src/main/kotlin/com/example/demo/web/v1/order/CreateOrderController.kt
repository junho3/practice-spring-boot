package com.example.demo.web.v1.order

import com.example.demo.web.v1.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/orders/response-status")
    fun responseStatus(): ApiResponse<FindOrderResponse> {
        return ApiResponse.success(FindOrderResponse(id = 1, memberName = "xxx", products = listOf()))
    }

    @PostMapping("/v1/orders/response-entity")
    fun responseEntity(): ResponseEntity<ApiResponse<FindOrderResponse>> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(FindOrderResponse(id = 1, memberName = "xxx", products = listOf())))
    }
}
