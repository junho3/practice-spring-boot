package com.example.demo.core.member.query

import com.example.demo.core.member.domain.Member
import com.example.demo.core.member.port.MemberRepository
import com.example.demo.createMember
import com.example.demo.infrastructure.persistence.MemberJpaRepository
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName("FindMemberCountService")
class FindMemberCountServiceTest(
    private val memberRepository: MemberRepository,
    private val memberJpaRepository: MemberJpaRepository,
    private val memberCountService: FindMemberCountService = FindMemberCountService(
        memberRepository = memberRepository,
    ),
) : DescribeSpec({

    describe("getMemberCountByChunk 메소드는") {
        val count = 1_000
        context("${count}명의 회원이 저장되어 있을 때") {

            val members: MutableList<Member> = mutableListOf()
            for (i in 1..count) {
                members.add(createMember())
            }

            memberJpaRepository.saveAll(members)

            it("전체회원 수 ${count}을 리턴한다.") {
                val result = memberCountService.getMemberCountByChunk(10)

                result shouldBe count
            }
        }
    }
})
