package com.example.etc

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec

@DisplayName("추상클래스의 open 프로퍼티와 abstract 프로퍼티 차이")
internal class OpenAndAbstractPropertyTest : FunSpec({
    test("테스트") {
        val pizza = Pizza(name2 = "Pepperoni")
        val apple = Fruit(name1 = "Apple", name2 = "Red")
        val cake = Cake(name1 = "딸기케잌", name2 = "초코케잌", name3 = "바나나케잌")

        println(pizza.name1)
        println(pizza.name2) // name2가 override 되어서 Pizza가 아니라 Pepperoni가 출력

        println(apple.name1)
        println(apple.name2) // Fruit.name1이 override가 없기 때문에 Yellow가 출력

        val castedPizza = pizza as Food
        println(castedPizza.name1)
        println(castedPizza.name2)
        println(castedPizza.name3)
    }
},)

// 추상 클래스 정의
abstract class Food(
    // name1: 비추상 프로퍼티
    val name1: String,
    // name2: 추상 프로퍼티, 오버라이드 가능
    open val name2: String,
) {
    // name3: 추상 프로퍼티, 반드시 오버라이드 필요
    abstract val name3: String
}

// 구현 클래스1
class Pizza(override val name2: String) : Food("Margherita", "Pizza") {
    override val name3: String = "Delicious Pizza"
}

// 구현 클래스2
class Fruit(name1: String, override val name2: String) : Food("Yellow", "Fruit") {
    override val name3: String = "Healthy Fruit"
}

class Cake(name1: String, override val name2: String, override val name3: String) : Food(name1, name2)
