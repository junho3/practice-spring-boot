package com.example.demo.infrastructure.persistence

import com.example.demo.core.order.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<Order, Long>
