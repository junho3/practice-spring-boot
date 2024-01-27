package com.example.demo.config.persistence

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ExposedDataSourceConfig(
    @Value("\${spring.datasource.writer.driver-class-name}")
    private val driverClassName: String,
    @Value("\${spring.datasource.writer.jdbc-url}")
    private val writerJdbcUrl: String,
    @Value("\${spring.datasource.reader.jdbc-url}")
    private val readerJdbcUrl: String,
    @Value("\${spring.datasource.writer.username}")
    private val userName: String,
    @Value("\${spring.datasource.writer.password}")
    private val password: String,
) {
    private val writeDatabase: Database by lazy {
        Database.connect(
            url = writerJdbcUrl,
            driver = driverClassName,
            user = userName,
            password = password,
        )
    }

    private val readDatabase: Database by lazy {
        Database.connect(
            url = readerJdbcUrl,
            driver = driverClassName,
            user = userName,
            password = password,
        )
    }

    fun write(): Database {
        return writeDatabase
    }

    fun read(): Database {
        return readDatabase
    }
}
