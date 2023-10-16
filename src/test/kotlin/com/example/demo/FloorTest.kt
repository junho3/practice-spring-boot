package com.example.demo

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.floor

@DisplayName("양수, 음수에 따른 반올림 테스트")
internal class FloorTest : FunSpec({

    test("4.5를 floor하면 4가 된다") {
        val actual = floor(4.5)

        actual shouldBe 4
    }

    test("-4.5를 floor하면 -5가 된다") {
        val actual = floor(-4.5)

        actual shouldBe -5
    }
})
