package com.example.demo.config.persistence

import com.example.demo.FixturesMonkey
import com.example.demo.IntegrationTest
import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.domain.OrderProduct
import com.example.demo.core.order.service.FindOrderService
import com.example.demo.infrastructure.persistence.order.OrderRepository
import com.navercorp.fixturemonkey.customizer.Values
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.set
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.hibernate.LazyInitializationException

@IntegrationTest
@DisplayName("TransactionalTest")
class TransactionalTest(
    private val orderRepository: OrderRepository,
    private val findOrderService: FindOrderService =
        FindOrderService(
            orderRepository = orderRepository,
        ),
) : DescribeSpec({

    beforeSpec {
        orderRepository.deleteAll()
    }

    describe("주문 데이터가 존재 했을 때") {

        beforeTest {
            val order = FixturesMonkey.fixture()
                .giveMeBuilder<Order>()
                .sample()

            (1..10).map {
                FixturesMonkey.fixture()
                    .giveMeBuilder<OrderProduct>()
                    .set(OrderProduct::order, Values.just(order))
                    .sample()
            }
                .forEach { order.addProduct(it) }

            orderRepository.save(order)
        }

        context("invoke 메소드를 사용하면") {

            it("Order 객체 조회 후, Lazy 로딩하여 products 객체에 접근한다.") {
                Transactional {
                    val result = findOrderService.findById(1)

                    result.shouldBeInstanceOf<Order>()
                    result.products.shouldNotBeEmpty()
                }
            }
        }

        context("invoke 메소드를 사용하지 않으면") {

            it("Order 객체 조회 후, Lazy 로딩에 실패하여 LazyInitializationException을 던진다.") {
                val result = findOrderService.findById(1)

                shouldThrow<LazyInitializationException> {
                    result.products.size shouldBe 1
                }
            }
        }
    }
},)
