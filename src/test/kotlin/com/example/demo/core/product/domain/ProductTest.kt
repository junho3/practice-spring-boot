package com.example.demo.core.product.domain

import com.example.demo.FixturesMonkey
import com.example.demo.PersistenceDataJpaTest
import com.example.demo.core.member.domain.Member
import com.example.demo.core.member.domain.Members
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import net.jqwik.api.Arbitraries
import java.math.BigDecimal
import kotlin.reflect.full.memberProperties

@DisplayName(name = "Product")
internal class ProductTest : FunSpec({

    test("Product 엔티티는 name이 공백일 때 IllegalArgumentException을 던진다.") {
        val name = ""
        shouldThrow<IllegalArgumentException> { Product(name = name, price = BigDecimal.ONE) }
    }

    test("Product 엔티티는 price가 0미만 일 때 IllegalArgumentException을 던진다.") {
        val price = FixturesMonkey
            .fixture()
            .giveMeBuilder<BigDecimal>()
            .set(Arbitraries.bigDecimals().lessOrEqual(BigDecimal.ZERO))
            .sample();

        shouldThrow<IllegalArgumentException> { Product(name = "사과", price = price) }
    }

    test("Product.name은 처음 세 글자가 '상품_'을 포함한다.") {
        val name = "사과"

        val actual = Product(name = name, price = BigDecimal.ONE)

        actual.name shouldBe "상품_사과"
    }
})
