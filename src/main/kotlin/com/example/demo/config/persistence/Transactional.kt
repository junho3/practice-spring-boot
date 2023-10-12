package com.example.demo.config.persistence

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Transactional {
    @Transactional
    operator fun <T> invoke(functionInTransaction: () -> T?): T? {
        return functionInTransaction()
    }
}
