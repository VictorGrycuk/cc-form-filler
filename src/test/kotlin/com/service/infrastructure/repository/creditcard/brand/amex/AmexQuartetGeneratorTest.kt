package com.service.infrastructure.repository.creditcard.brand.amex

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class AmexQuartetGeneratorTest {
    private val generator = AmexQuartetGenerator()
    private val firstRange = 3400..3499
    private val secondRange = 3700..3799

    @Test
    fun `should generate correct amex numbers`() {
        (1..1000)
            .map { generator.generate() }
            .map { number ->
                assertThat(number in firstRange || number in secondRange).isTrue()
            }
    }
}