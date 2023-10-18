package com.example.demo.infrastructure.persistence.member

import com.example.demo.core.member.domain.Member
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.SQLException

@Repository
class MemberJdbcRepository(
    private val jdbcTemplate: JdbcTemplate,
) {
    fun saveAll(members: List<Member>) {
        jdbcTemplate.batchUpdate(
            "insert into member (member_name) values (?)",
            object : BatchPreparedStatementSetter {
                @Throws(SQLException::class)
                override fun setValues(ps: PreparedStatement, i: Int) {
                    ps.setString(1, members[i].name)
                }

                override fun getBatchSize(): Int {
                    return members.size
                }
            }
        )
    }
}
