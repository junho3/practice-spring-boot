package com.example.demo.infrastructure.persistence

import com.example.demo.core.member.domain.Member
import com.example.demo.createMember
import com.example.demo.infrastructure.persistence.member.MemberExposedRepository
import com.example.demo.infrastructure.persistence.member.MemberJdbcRepository
import com.example.demo.infrastructure.persistence.member.MemberJpaRepository
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("SaveMassMemberDataTest")
class SaveMassMemberDataTest(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberJdbcRepository: MemberJdbcRepository,
    private val memberExposedRepository: MemberExposedRepository,
) : FunSpec({

    val count = 1_000

    test("1000명의 회원 데이터를 Jpa Save()로 저장했을 때") {
        for (i in 1..count) {
            memberJpaRepository.save(createMember())
        }
    }

    test("1000명의 회원 데이터를 Jpa SaveAll()로 저장했을 때") {
        val members: MutableList<Member> = mutableListOf()
        for (i in 1..count) {
            members.add(createMember())
        }

        memberJpaRepository.saveAll(members)
    }

    test("1000명의 회원 데이터를 JdbcTemplate으로 저장했을 때") {
        val members: MutableList<Member> = mutableListOf()
        for (i in 1..count) {
            members.add(createMember())
        }

        memberJdbcRepository.saveAll(members)
    }

    test("1000명의 회원 데이터를 Exposed batchInsert()로 저장했을 때") {
        val members: MutableList<Member> = mutableListOf()
        for (i in 1 .. count) {
            members.add(createMember())
        }

        memberExposedRepository.saveAll(members)
    }
})
