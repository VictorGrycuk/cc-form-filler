package com.service.infrastructure.repository.creditcard.expiration

import assertk.assertThat
import assertk.assertions.isBetween
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExpirationDateGeneratorTest {
    private val generator = ExpirationDateGenerator()
    private val currentYear = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy")).toInt()
    private val maxYear = currentYear + 10

    @Test
    fun `should generate expiration date within the specified dates` () {
        (0..1000)
            .map { generator.generate() }
            .map { date ->
                assertThat(date.month).isBetween(1, 12)
                assertThat(date.year).isBetween(currentYear, maxYear)
            }
    }
}