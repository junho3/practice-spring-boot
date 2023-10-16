package com.example.demo

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
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
})
