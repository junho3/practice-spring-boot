package com.example.demo.config.persistence

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExposedDataSourceConfig(
    @Value("\${spring.datasource.writer.driver-class-name}")
    private val driverClassName: String,
    @Value("\${spring.datasource.writer.jdbc-url}")
    private val jdbcUrl: String,
    @Value("\${spring.datasource.writer.username}")
    private val userName: String,
    @Value("\${spring.datasource.writer.password}")
    private val password: String,
) {
    private val writeDatabase: Database by lazy {
        Database.connect(
            url = jdbcUrl,
            driver = driverClassName,
            user = userName,
            password = password,
        )
    }

    fun write(): Database {
        return writeDatabase
    }
}
