package com.example.demo.core.order.query

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.port.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindOrderService(
    private val orderRepository: OrderRepository
) {
    @Transactional(readOnly = true)
    fun findById(id: Long): Order {
        return orderRepository.findById(id)
            ?: throw IllegalArgumentException("주문정보가 존재하지 않습니다.")
    }
}
