package com.example.etc

import io.kotest.core.spec.style.FunSpec
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

internal class YearAndMonthTest : FunSpec({

    test("년월만 주어졌을 때 LocalDate 변환 테스트") {
        val yyMM = "202405"
        val formatter = DateTimeFormatter.ofPattern("yyyyMM")
        val date = YearMonth.parse(yyMM, formatter)

        val firstDay: LocalDate = date.atDay(1)
        val lastDay: LocalDate = date.atEndOfMonth()


        println(date)
    }
})
