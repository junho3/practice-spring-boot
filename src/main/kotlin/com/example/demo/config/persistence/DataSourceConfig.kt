package com.example.demo.config.persistence

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    /**
     * 읽기 전용 DB properties 내용을 파싱하여 리턴.
     *
     * @return 읽기 전용 DB의 Properties
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.reader")
    fun readDataSourceProperties(): HikariConfig {
        return HikariConfig()
    }

    /**
     * 쓰기 전용 DB properties 내용을 파싱하여 리턴.
     *
     * @return 쓰기 전용 DB의 Properties
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.writer")
    fun writeDataSourceProperties(): HikariConfig {
        return HikariConfig()
    }

    /**
     * 여러 DataSource를 Routing 하기위해 Map에 저장.
     *
     * @return 여러 DataSource 객체가 담긴 Map
     */
    @DependsOn("writeDataSourceProperties", "readDataSourceProperties")
    @Bean
    fun dynamicRoutingDataSource(): DynamicRoutingDataSource {
        val writeDataSource: DataSource = createDataSource(writeDataSourceProperties())
        val readDataSource: DataSource = createDataSource(readDataSourceProperties())

        val dataSourceMap = HashMap<Any, Any>().also {
            it[DataSourceType.WRITE] = writeDataSource
            it[DataSourceType.READ] = readDataSource
        }

        val routingDataSource = DynamicRoutingDataSource().also {
            it.setTargetDataSources(dataSourceMap)
            it.setDefaultTargetDataSource(writeDataSource)
        }

        return routingDataSource
    }

    /**
     * 연결할 DataSource를 설정.
     *
     * @param dynamicRoutingDataSource 연결할 DataSource
     */
    @DependsOn("dynamicRoutingDataSource")
    @Primary
    @Bean
    fun dataSource(dynamicRoutingDataSource: DynamicRoutingDataSource): DataSource {
        return LazyConnectionDataSourceProxy(dynamicRoutingDataSource)
    }

    /**
     * HikariDataSource 객체를 생성하여 리턴.
     *
     * @param dataSourceProperty HikariDataSource 객체를 생성하려는 DB의 Properties
     * @return HikariDataSource 객체
     */
    private fun createDataSource(dataSourceProperty: HikariConfig): HikariDataSource {
        return HikariDataSource(dataSourceProperty)
    }
}

/**
 * 동적으로 여러 DataSource 중 하나로 연결.
 */
class DynamicRoutingDataSource : AbstractRoutingDataSource() {

    /**
     * Lookup Key를 반환하여 Lookup Key에 대응하는 DataSource를 사용하도록 연결한다.
     */
    override fun determineCurrentLookupKey(): DataSourceType {
        return if (isTransaction()) DataSourceType.WRITE else DataSourceType.READ
    }

    private fun isTransaction() = TransactionSynchronizationManager.isActualTransactionActive() &&
        !TransactionSynchronizationManager.isCurrentTransactionReadOnly()
}

/**
 * DataSource 타입.
 * */
enum class DataSourceType(private val description: String) {
    WRITE("Write 전용 DB"),
    READ("Read 전용 DB")
}
