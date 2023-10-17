package com.example.demo.core.order.service

import com.example.demo.core.order.domain.Order
import com.example.demo.infrastructure.persistence.order.OrderRepository
import com.example.demo.createOrder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName(name = "FindOrderService")
class FindOrderServiceTest(
    private val orderRepository: OrderRepository,
    private val findOrderService: FindOrderService = FindOrderService(
        orderRepository = orderRepository,
    ),
) : DescribeSpec({

    beforeSpec {
        orderRepository.deleteAll()
    }

    describe("findById 메소드는") {
        context("주문 데이터가 존재하면") {
            orderRepository.save(createOrder())

            it("Order 객체를 리턴한다.") {
                val result = findOrderService.findById(1)

                result.shouldBeInstanceOf<Order>()
            }
        }

        context("주문 데이터가 존재하지 않으면") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> { findOrderService.findById(2) }

                exception.shouldBeInstanceOf<IllegalArgumentException>()
            }
        }
    }
})
