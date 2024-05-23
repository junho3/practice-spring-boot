package com.example.etc

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@DisplayName("YearAndMonthTest")
internal class DateTimeTest : FunSpec({

    test("문자열 타입의 년월만 주어졌을 때 LocalDate 변환 테스트") {
        val yyMM = "202405"
        val formatter = DateTimeFormatter.ofPattern("yyyyMM")
        val yearMonth = YearMonth.parse(yyMM, formatter)

        val firstDay: LocalDate = yearMonth.atDay(1)
        val lastDay: LocalDate = yearMonth.atEndOfMonth()

        println(yearMonth)
        println(firstDay)
        println(lastDay)
    }

    test("LocalDateTime.MAX, LocalDate.now().atTime(23,59,59) 비교 테스트") {
        val max = LocalDateTime.MAX
        val atTime = LocalDate.now().atTime(23,59,59)
        val atTimeMax = LocalDate.now().atTime(LocalTime.MAX)

        println(max)
        println(atTime)
        println(atTimeMax)
    }
})
