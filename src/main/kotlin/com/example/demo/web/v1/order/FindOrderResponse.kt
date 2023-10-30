package com.example.demo.web.v1.order

import com.example.demo.core.order.domain.Order
import com.example.demo.core.order.domain.OrderProduct

data class FindOrderResponse(
    val id: Long,
    val memberName: String,
    val products: List<Product>
) {
    data class Product(
        val id: Long,
        val name: String,
        val quantity: Long,
    ) {
        companion object {
            fun from(product: OrderProduct): Product = with(product) {
                return Product(
                    id = id!!,
                    name = name,
                    quantity = quantity,
                )
            }
        }
    }

    companion object {
        fun from(order: Order): FindOrderResponse = with(order) {
            return FindOrderResponse(
                id = id!!,
                memberName = memberName,
                products = products.map { Product.from(it) }, // N+1 이슈가 있으므로 운영환경 코드로는 적합하지 않음
            )
        }
    }
}
