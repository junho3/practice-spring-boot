package com.example.etc

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ceil

@DisplayName("양수, 음수에 따른 올림 테스트")
internal class CeilTest : FunSpec({
    test("4.5를 ceil하면 5가 된다") {
        val actual = ceil(4.5)

        actual shouldBe 5
    }

    test("-4.5를 ceil하면 -4가 된다") {
        val actual = ceil(-4.5)

        actual shouldBe -4
    }

    test("BigDecimal 4.5를 setScale로 UP하면 5가 된다.") {
        val actual = BigDecimal("4.5").setScale(0, RoundingMode.UP)

        actual shouldBe BigDecimal("5")
    }

    test("BigDecimal -4.5를 setScale로 UP하면 -5가 된다.") {
        val actual = BigDecimal("-4.5").setScale(0, RoundingMode.UP)

        actual shouldBe BigDecimal("-5")
    }

    test("BigDecimal 4.5를 setScale로 CEILING하면 5가 된다.") {
        val actual = BigDecimal("4.5").setScale(0, RoundingMode.CEILING)

        actual shouldBe BigDecimal("5")
    }

    test("BigDecimal -4.5를 setScale로 CEILING하면 -4가 된다.") {
        val actual = BigDecimal("-4.5").setScale(0, RoundingMode.CEILING)

        actual shouldBe BigDecimal("-4")
    }
})
