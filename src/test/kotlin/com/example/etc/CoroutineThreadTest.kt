package com.example.etc

import io.kotest.core.spec.style.FunSpec
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging

internal class CoroutineThreadTest(
    private val coroutineClass: CoroutineClass = CoroutineClass(),
) : FunSpec({

        test("코루틴 스레드 테스트") {
            coroutineClass.foo()
        }
    })

class CoroutineClass {
    suspend fun foo() {
        val mainThreadName = Thread.currentThread().name

        // pool-1-thread-1 @coroutine#3
        logger.info { "Job 1: $mainThreadName" }

        coroutineScope {
            val threadName = Thread.currentThread().name

            // pool-1-thread-1 @coroutine#3
            logger.info { "Job 2: $threadName" }
        }

        // 새로운 스레드가 생성 됨
        CoroutineScope(Dispatchers.Default).launch {
            val threadName = Thread.currentThread().name

            // DefaultDispatcher-worker-1 @coroutine#4
            logger.info { "Job 3: $threadName" }
        }

        // pool-1-thread-1 @coroutine#3
        logger.info { "Job 4: $mainThreadName" }
    }
}

val logger = KotlinLogging.logger {}
