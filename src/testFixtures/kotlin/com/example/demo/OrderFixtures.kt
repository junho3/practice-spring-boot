package com.example.demo

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.domain.OrderProduct
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.customizer.Values
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.set

fun createOrder(memberName: String = "어피치"): Order {
    val order = FixtureMonkey
        .builder()
        .plugin(KotlinPlugin())
        .build()
        .giveMeBuilder<Order>()
        .sample()

    (1..10).map {
        FixtureMonkey.builder()
            .plugin(KotlinPlugin())
            .build()
            .giveMeBuilder<OrderProduct>()
            .set(OrderProduct::order, Values.just(order))
            .sample()
    }
        .forEach { order.addProduct(it) }

    return order
}
