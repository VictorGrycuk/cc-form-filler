package com.service.infrastructure.repository.creditcard.expiration

import com.service.core.domain.creditcard.CreditCardExpiration
import com.service.infrastructure.repository.creditcard.getRandomInt
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExpirationDateGenerator(
    private val monthMin: Int = 1,
    private val monthMax: Int = 12,
    private val yearMax: Int = 10,
) {
    fun generate(): CreditCardExpiration {
        val generatedYear = generateYear()
        val generatedMonth = generateMonth(generatedYear)

        return CreditCardExpiration(
            month = generatedMonth,
            year = generatedYear,
        )
    }

    private fun generateYear(): Int {
        val currentYear = getCurrentYear()
        return getRandomInt(
            currentYear,
            currentYear + getRandomInt(0, yearMax)
        )
    }

    private fun generateMonth(generatedYear: Int): Int {
        val currentYear = getCurrentYear()
        val currentMonth = getCurrentMonth()

        // We need to avoid to generate a date with the same year and past month
        return if (currentYear == generatedYear) getRandomInt(currentMonth, monthMax)
            else getRandomInt(monthMin, monthMax)
    }

    private fun getCurrentYear() =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy")).toInt()

    private fun getCurrentMonth() =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")).toInt()
}