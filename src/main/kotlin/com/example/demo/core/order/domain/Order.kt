package com.example.demo.core.order.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "member_name")
    val memberName: String,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "order_product_id")
    val products: MutableList<OrderProduct> = mutableListOf(),
)
