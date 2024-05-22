package com.example.etc

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@DisplayName("YearAndMonthTest")
internal class YearAndMonthTest : FunSpec({

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
})
