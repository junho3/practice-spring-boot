package com.example.demo

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.domain.OrderProduct

fun createOrder(
    memberName: String = "어피치",
    products: MutableList<OrderProduct> = mutableListOf(createOrderProduct()),
): Order {
    return Order(
        memberName = memberName,
        products = products,
    )
}

fun createOrderProduct(
    name: String = "카스타드",
    quantity: Long = 1,
): OrderProduct {
    return OrderProduct(
        name = name,
        quantity = quantity,
    )
}
