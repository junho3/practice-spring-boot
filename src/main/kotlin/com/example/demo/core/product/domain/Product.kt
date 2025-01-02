package com.example.demo.core.product.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "product")
class Product private constructor(
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "product_name", nullable = false)
    val name: String,

    @Column(name = "product_price", nullable = false)
    val price: BigDecimal,
) {
    companion object {
        operator fun invoke(
            name: String,
            price: BigDecimal,
        ): Product {
            require(price > BigDecimal.ONE) { "Price must not be zero or negative" }

            return Product(
                name = name,
                price = price,
            )
        }
    }
}
