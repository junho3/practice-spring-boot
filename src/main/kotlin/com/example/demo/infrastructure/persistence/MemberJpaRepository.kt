package com.example.demo.infrastructure.persistence

import com.example.demo.core.member.domain.Member
import com.example.demo.core.member.port.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : MemberRepository, JpaRepository<Member, Long> {

    override fun findAll(pageable: Pageable): Page<Member>
}
