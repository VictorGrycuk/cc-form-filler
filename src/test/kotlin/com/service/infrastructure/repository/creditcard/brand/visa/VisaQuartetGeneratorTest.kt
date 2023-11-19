package com.service.infrastructure.repository.creditcard.brand.visa

import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

class VisaQuartetGeneratorTest {
    private val generator = VisaQuartetGenerator()
    private val validRange = 4000..4999

    @Test
    fun `should generate correct visa numbers`() {
        (1..1000)
            .map { generator.generate() }
            .map { number -> assertTrue(number in validRange) }
    }
}