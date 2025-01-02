package com.example.demo.common.enums.product

enum class ProductStatus private constructor(val description: String) {
    READY("판매준비"),
    SELLING("판매중"),
    SOLD_OUT("품절"),
    END("판매종료"),
}
