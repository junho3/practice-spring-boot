package com.example.etc

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import kotlin.properties.Delegates

@DisplayName("변수 선언과 초기화 테스트")
internal class VariableDeclareAndInitializeTest : FunSpec({
    test("val은 변수를 초기화하지 않으면 빌드를 할 수 없다.") {
        val number: Int
        number = 1

        println(number)
    }

    test("var는 변수를 초기화하지 않으면 빌드를 할 수 없다.") {
        var number: Int
        number = 1

        println(number)
    }

    test("lateinit var는 변수를 초기화하지 않으면 UninitializedPropertyAccessException을 던진다.") {
        // lateinit var는 기본타입 불가능
//        lateinit var number: Int
        lateinit var text: String

        shouldThrow<UninitializedPropertyAccessException> { println(text) }
    }

    test("Delegates는 변수를 초기화하지 않으면 IllegalStateException을 던진다.") {
        // lateinit var는 기본타입이 불가능하여 delegates로 해야 함
        var  number by Delegates.notNull<Int>()

        shouldThrow<IllegalStateException> { println(number) }
    }
})
