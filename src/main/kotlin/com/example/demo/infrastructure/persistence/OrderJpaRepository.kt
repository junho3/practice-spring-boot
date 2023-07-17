package com.example.demo.infrastructure.persistence

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.port.OrderRepository
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : OrderRepository, JpaRepository<Order, Long>
