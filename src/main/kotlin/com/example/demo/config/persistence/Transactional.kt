package com.example.demo.config.persistence

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Transactional(
    _transactionalAdvice: TransactionalAdvice
) {

    init {
        transactional = _transactionalAdvice
    }

    companion object {
        private lateinit var transactional: TransactionalAdvice

        operator fun <T> invoke(function: () -> T): T {
            return transactional { function() }
        }
    }

    @Component
    class TransactionalAdvice {
        @Transactional
        operator fun <T> invoke(function: () -> T): T {
            return function()
        }
    }
}
