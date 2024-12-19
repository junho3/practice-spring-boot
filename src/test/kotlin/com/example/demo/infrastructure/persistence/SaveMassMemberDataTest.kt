package com.example.demo.infrastructure.persistence

import com.example.demo.FixturesMonkey
import com.example.demo.IntegrationTest
import com.example.demo.core.member.domain.Member
import com.example.demo.core.order.domain.Order
import com.example.demo.infrastructure.persistence.member.MemberExposedRepository
import com.example.demo.infrastructure.persistence.member.MemberJdbcRepository
import com.example.demo.infrastructure.persistence.member.MemberJpaRepository
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec

@IntegrationTest
@DisplayName("SaveMassMemberDataTest")
internal class SaveMassMemberDataTest(
    private val memberJpaRepository: MemberJpaRepository,
    private val memberJdbcRepository: MemberJdbcRepository,
    private val memberExposedRepository: MemberExposedRepository,
) : FunSpec({

        val count = 1_000

        test("1000명의 회원 데이터를 Jpa Save()로 저장했을 때") {
            for (i in 1..count) {
                val member = FixturesMonkey.fixture()
                    .giveMeBuilder<Member>()
                    .sample()

                memberJpaRepository.save(member)
            }
        }

        test("1000명의 회원 데이터를 Jpa SaveAll()로 저장했을 때") {
            val members = FixturesMonkey.fixture()
                .giveMeBuilder<Member>()
                .sampleList(count)

            memberJpaRepository.saveAll(members)
        }

        test("1000명의 회원 데이터를 JdbcTemplate으로 저장했을 때") {
            val members = FixturesMonkey.fixture()
                .giveMeBuilder<Member>()
                .sampleList(count)

            memberJdbcRepository.saveAll(members)
        }

        test("1000명의 회원 데이터를 Exposed batchInsert()로 저장했을 때") {
            val members = FixturesMonkey.fixture()
                .giveMeBuilder<Member>()
                .sampleList(count)

            memberExposedRepository.saveAll(members)
        }
    })
