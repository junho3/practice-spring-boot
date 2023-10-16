package com.example.demo

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.round
import kotlin.math.roundToLong

@DisplayName("코틀린 반올림 함수별 반올림 테스트")
class RoundTest : FunSpec({

    test("4.5를 round하면 4가 된다.") {
        val actual = round(4.5)

        actual shouldBe 4
    }

    test("-4.5를 round하면 -4가 된다.") {
        val actual = round(-4.5)

        actual shouldBe -4
    }

    test("4.5를 roundToLong하면 5가 된다.") {
        val actual = 4.5.roundToLong()

        actual shouldBe 5
    }

    test("-4.5를 roundToLong하면 -5가 된다.") {
        val actual = -4.5.roundToLong()

        actual shouldBe -5
    }
})
