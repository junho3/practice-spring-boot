package com.example.demo.core.member

import com.example.demo.core.member.domain.Member
import com.example.demo.createMember
import com.example.demo.infrastructure.persistence.member.MemberJdbcRepository
import com.example.demo.infrastructure.persistence.member.MemberJpaRepository
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate

@DataJpaTest
@DisplayName("SaveMassMemberDataTest")
class SaveMassMemberDataTest(
    private val jdbcTemplate: JdbcTemplate,
    private val memberJpaRepository: MemberJpaRepository,
    private val memberJdbcRepository: MemberJdbcRepository = MemberJdbcRepository(jdbcTemplate = jdbcTemplate),
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
})
