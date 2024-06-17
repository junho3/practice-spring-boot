package com.example.etc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs

internal class MutableTest : FunSpec({
    test("Call by reference에 의해 a와 b 모두 부산으로 변경된다.") {
        val a = MutableClass("서울")
        val b = a

        b.value = "부산"

        a.value shouldBe "부산"
        b.value shouldBe "부산"
    }

    test("b의 mutableClass에 새로운 MutableClass 객체를 set을 하므로 b만 부산으로 변경된다.") {
        val mutableClass = MutableClass("서울")
        val a = MainClass(mutableClass)
        val b = MainClass(mutableClass)

        b.mutableClass = MutableClass("부산")

        a.mutableClass.value shouldBe "서울"
        b.mutableClass.value shouldBe "부산"
    }

    test("b의 mutableClass에 새로운 MutableClass 객체를 set하여 a.mutableClass와 b.mutableClass는 다른 클래스이다.") {
        val mutableClass = MutableClass("서울")
        val a = MainClass(mutableClass)
        val b = MainClass(mutableClass)

        a.mutableClass.shouldBeSameInstanceAs(b.mutableClass)

        b.mutableClass = MutableClass("서울")

        a.mutableClass.shouldNotBeSameInstanceAs(b.mutableClass)
    }

    test("Call by value에 의해 b만 부산으로 변경된다.") {
        val a = "서울"
        var b = a

        b = "부산"

        a shouldBe "서울"
        b shouldBe "부산"
    }
})

class MutableClass(var value: String)

class MainClass(var mutableClass: MutableClass)
