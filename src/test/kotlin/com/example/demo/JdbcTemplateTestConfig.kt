package com.example.demo

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@TestConfiguration
class JdbcTemplateTestConfig {
    @Bean
    fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate()
    }
}
