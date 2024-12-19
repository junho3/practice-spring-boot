package com.example.demo.core.member.service

import com.example.demo.FixturesMonkey
import com.example.demo.PersistenceDataJpaTest
import com.example.demo.core.member.domain.Member
import com.example.demo.infrastructure.persistence.member.MemberJpaRepository
import com.example.demo.infrastructure.persistence.member.MemberRepository
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

@PersistenceDataJpaTest
@DisplayName("FindMemberCountService")
internal class FindMemberCountServiceTest(
    private val memberRepository: MemberRepository,
    private val memberJpaRepository: MemberJpaRepository,
    private val memberCountService: FindMemberCountService =
        FindMemberCountService(
            memberRepository = memberRepository,
        ),
) : DescribeSpec({

        beforeSpec { memberJpaRepository.deleteAll() }

        describe("getMemberCountByChunk 메소드는") {

            context("1000명의 회원이 존재했을 때") {

                beforeTest {
                    val members = FixturesMonkey.fixture()
                        .giveMeBuilder<Member>()
                        .sampleList(1_000)

                    memberJpaRepository.saveAll(members)
                }

                it("전체회원 수 1000을 리턴한다.") {
                    val result = memberCountService.getMemberCountByChunk(10)

                    result shouldBe 1_000
                }
            }
        }
    })
