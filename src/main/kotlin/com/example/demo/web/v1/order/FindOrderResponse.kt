package com.example.demo.web.v1.order

data class FindOrderResponse(
    val id: Long,
    val memberName: String,
    val products: List<Product>
) {
    data class Product(
        val id: Long,
        val name: String,
        val quantity: Long,
    )
}
