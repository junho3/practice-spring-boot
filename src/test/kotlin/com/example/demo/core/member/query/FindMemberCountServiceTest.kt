package com.example.demo.core.member.query

import com.example.demo.core.member.domain.Member
import com.example.demo.core.member.port.MemberRepository
import com.example.demo.createMember
import com.example.demo.infrastructure.persistence.member.MemberJdbcRepository
import com.example.demo.infrastructure.persistence.member.MemberJpaRepository
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate

@DataJpaTest
@DisplayName("FindMemberCountService")
class FindMemberCountServiceTest(
    private val jdbcTemplate: JdbcTemplate,
    private val memberRepository: MemberRepository,
    private val memberJpaRepository: MemberJpaRepository,
    private val memberJdbcRepository: MemberJdbcRepository = MemberJdbcRepository(jdbcTemplate = jdbcTemplate),
    private val memberCountService: FindMemberCountService = FindMemberCountService(
        memberRepository = memberRepository,
    ),
) : DescribeSpec({

    describe("getMemberCountByChunk 메소드는") {
        val count = 1_000
        val members: MutableList<Member> = mutableListOf()
        for (i in 1..count) {
            members.add(createMember())
        }

        afterTest { memberJpaRepository.deleteAll() }

        context("1000명의 회원을 Jpa SaveAll()로 저장했을 때") {

            memberJpaRepository.saveAll(members)

            it("전체회원 수 1000을 리턴한다.") {
                val result = memberCountService.getMemberCountByChunk(10)

                result shouldBe count
            }
        }

        context("1000명의 회원을 Jpa Save()로 저장했을 때") {

            for (i in 1..count) {
                memberJpaRepository.save(createMember())
            }

            it("전체회원 수 1000을 리턴한다.") {
                val result = memberCountService.getMemberCountByChunk(10)

                result shouldBe count
            }
        }

        context("1000명의 회원을 JdbcTemplate으로 저장했을 때") {

            memberJdbcRepository.saveAll(members)

            it("전체회원 수 1000을 리턴한다.") {
                val result = memberCountService.getMemberCountByChunk(10)

                result shouldBe count
            }
        }
    }
})
