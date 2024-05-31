package com.example.demo.core.order.service

import com.example.demo.FixturesMonkey
import com.example.demo.PersistenceDataJpaTest
import com.example.demo.core.order.domain.Order
import com.example.demo.infrastructure.persistence.order.OrderRepository
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.types.shouldBeInstanceOf

@PersistenceDataJpaTest
@DisplayName(name = "FindOrderService")
internal class FindOrderServiceTest(
    private val orderRepository: OrderRepository,
    private val findOrderService: FindOrderService =
        FindOrderService(
            orderRepository = orderRepository,
        ),
) : DescribeSpec({

    beforeSpec {
        orderRepository.deleteAll()
    }

    describe("findById()는") {

        context("주문 데이터가 존재하면") {

            beforeTest {
                val order = FixturesMonkey.fixture()
                    .giveMeBuilder<Order>()
                    .sample()

                orderRepository.save(order)
            }

            it("Order 객체를 리턴한다.") {
                val result = findOrderService.findById(1)

                result.shouldBeInstanceOf<Order>()
            }
        }

        context("주문 데이터가 존재하지 않으면") {

            beforeTest { orderRepository.deleteAll() }

            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> { findOrderService.findById(1) }

                exception.shouldBeInstanceOf<IllegalArgumentException>()
            }
        }
    }
},)
