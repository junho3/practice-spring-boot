package com.example.demo.core.order.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Column(name = "member_name")
    val memberName: String,
) {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "order")
    private val _products: MutableList<OrderProduct> = mutableListOf()

    val products: List<OrderProduct>
        get() = _products.toList()

    fun addProduct(product: OrderProduct) {
        _products.add(product)
    }
}
