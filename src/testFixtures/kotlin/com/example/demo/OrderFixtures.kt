package com.example.demo

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.domain.OrderProduct

fun createOrder(memberName: String = "어피치"): Order {
    val order = Order(memberName = memberName)
    listOf(createOrderProduct(order = order))
        .forEach { order.addProduct(it) }

    return order
}

fun createOrderProduct(
    name: String = "카스타드",
    quantity: Long = 1,
    order: Order,
): OrderProduct {
    return OrderProduct(
        name = name,
        quantity = quantity,
        order = order,
    )
}
