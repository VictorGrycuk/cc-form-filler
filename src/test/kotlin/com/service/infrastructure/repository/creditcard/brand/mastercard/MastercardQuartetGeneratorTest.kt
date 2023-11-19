package com.service.infrastructure.repository.creditcard.brand.mastercard

import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

class MastercardQuartetGeneratorTest {
    private val generator = MastercardQuartetGenerator()
    private val validRange = 5100..5599

    @Test
    fun `should generate correct mastercard numbers`() {
        (1..1000)
            .map { generator.generate() }
            .map { number -> assertTrue(number in validRange) }
    }
}