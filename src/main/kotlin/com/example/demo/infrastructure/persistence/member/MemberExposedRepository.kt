package com.example.demo.infrastructure.persistence.member

import com.example.demo.core.member.domain.Member

interface MemberExposedRepository {

    fun saveAll(members: List<Member>)
}
