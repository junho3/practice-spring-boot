package com.example.demo.web.v1.order

import com.example.demo.core.order.service.FindOrderService
import com.example.demo.web.v1.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindOrderController(
    private val findOrderService: FindOrderService,
) {
    @GetMapping("/v1/orders/{id}")
    fun findById(
        @PathVariable("id") id: Long,
    ): ApiResponse<FindOrderResponse> {
        val data =
            findOrderService.findById(id)
                .let { FindOrderResponse.from(it) }

        return ApiResponse.success(data)
    }
}
