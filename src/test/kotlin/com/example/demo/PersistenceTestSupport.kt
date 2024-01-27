package com.example.demo

import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.EntityTransaction
import org.springframework.beans.factory.annotation.Autowired

abstract class PersistenceTestSupport(body: DescribeSpec.() -> Unit = {}) : DescribeSpec(body) {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    protected lateinit var entityManagerFactory: EntityManagerFactory

    @Autowired
    protected lateinit var query: JPAQueryFactory

    private val entityManager: EntityManager by lazy {
        entityManagerFactory.createEntityManager()
    }

    private val transaction: EntityTransaction by lazy {
        entityManager.transaction
    }

    protected fun <T> save(entity: T): T {
        transaction.begin()

        try {
            entityManager.persist(entity)
            entityManager.flush() // transaction commit시 자동으로 flush 발생시키나 명시적으로 선언
            transaction.commit()
            entityManager.clear()
        } catch (e: Exception) {
            transaction.rollback()
            throw e
        }

        return entity
    }

    protected fun <T> saveAll(entities: Iterable<T>): Iterable<T> {
        transaction.begin()

        try {
            for (entity in entities) {
                entityManager.persist(entity)
            }
        } catch (e: Exception) {
            transaction.rollback()
            throw e
        } finally {
            entityManager.flush() // transaction commit시 자동으로 flush 발생시키나 명시적으로 선언
            transaction.commit()
            entityManager.clear()
        }

        return entities
    }
}
