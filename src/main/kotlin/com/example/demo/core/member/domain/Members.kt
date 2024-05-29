package com.example.demo.core.member.domain

import org.jetbrains.exposed.dao.id.LongIdTable

object Members : LongIdTable(
    name = "MEMBER",
    columnName = "member_id",
) {
    val name = varchar(name = "member_name", length = 64)
}
