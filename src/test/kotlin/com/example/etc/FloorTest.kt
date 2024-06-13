package com.example.etc

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor

@DisplayName("양수, 음수에 따른 내림 테스트")
internal class FloorTest : FunSpec({

    test("4.5를 floor하면 4가 된다") {
        val actual = floor(4.5)

        actual shouldBe 4
    }

    test("-4.5를 floor하면 -5가 된다") {
        val actual = floor(-4.5)

        actual shouldBe -5
    }

    test("BigDecimal 4.5를 setScale로 DOWN 하면 5가 된다.") {
        val actual = BigDecimal("4.5").setScale(0, RoundingMode.DOWN)

        actual shouldBe BigDecimal("4")
    }

    test("BigDecimal -4.5를 setScale로 DOWN 하면 -5가 된다.") {
        val actual = BigDecimal("-4.5").setScale(0, RoundingMode.DOWN)

        actual shouldBe BigDecimal("-4")
    }

    test("BigDecimal 4.5를 setScale로 FLOOR 하면 5가 된다.") {
        val actual = BigDecimal("4.5").setScale(0, RoundingMode.FLOOR)

        actual shouldBe BigDecimal("4")
    }

    test("BigDecimal -4.5를 setScale로 FLOOR 하면 -5가 된다.") {
        val actual = BigDecimal("-4.5").setScale(0, RoundingMode.FLOOR)

        actual shouldBe BigDecimal("-5")
    }
})
