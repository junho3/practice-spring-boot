package com.example.demo.core.member.domain

import com.example.demo.core.product.domain.Product
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

@DisplayName(name = "Member")
internal class MemberTest : FunSpec({

    test("Member 엔티티는 name이 공백일 때 IllegalArgumentException을 던진다.") {
        val name = ""
        shouldThrow<IllegalArgumentException> { Member(name = name) }
    }
})
