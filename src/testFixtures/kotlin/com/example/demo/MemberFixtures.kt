package com.example.demo

import com.example.demo.core.member.domain.Member

fun createMember(
    id: Long = 0,
    name: String = "춘식이",
): Member {
    return Member(
        id = id,
        name = name
    )
}
