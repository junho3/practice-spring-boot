package com.example.demo.core.member.service

import com.example.demo.infrastructure.persistence.member.MemberRepository
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FindMemberCountService(
    private val memberRepository: MemberRepository,
) {

    fun getMemberCountByChunk(chunkSize: Int): Int {
        var page = 0
        var count = 0

        do {
            val pageable = PageRequest.of(page, chunkSize)
            val memberPages = memberRepository.findAll(pageable)

            memberPages.content.forEach {
                count++
                logger.info { "count: $count | name: ${it.name}" }
            }

            page++
        } while (memberPages.hasNext())

        return count
    }
}

private val logger = KotlinLogging.logger {}
