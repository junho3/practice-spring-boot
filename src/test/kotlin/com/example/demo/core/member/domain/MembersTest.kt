package com.example.demo.core.member.domain

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.reflect.full.memberProperties

@DisplayName("Members")
internal class MembersTest : FunSpec({
    test("Member Jpa와 Exposed의 엔티티 필드는 동일해야 한다.") {
        val jpaProperties = Member::class.memberProperties.map { it.name }
        val exposedProperties = Members::class.memberProperties.map { it.name }

        jpaProperties.forEach { property ->
            exposedProperties.any { it == property } shouldBe true
        }
    }
})
