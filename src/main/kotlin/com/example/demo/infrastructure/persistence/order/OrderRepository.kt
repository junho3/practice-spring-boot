package com.example.demo.infrastructure.persistence.order

import com.example.demo.core.order.domain.Order

interface OrderRepository {
    fun findById(id: Long): Order?

    fun save(order: Order): Order

    fun deleteAll()
}
