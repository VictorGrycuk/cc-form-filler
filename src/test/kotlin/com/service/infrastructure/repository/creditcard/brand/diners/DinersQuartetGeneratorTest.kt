package com.service.infrastructure.repository.creditcard.brand.diners

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class DinersQuartetGeneratorTest {
    private val generator = DinersQuartetGenerator()
    private val firstRange = 3000..3059
    private val secondRange = 3600..3699
    private val thirdRange = 3800..3899

    @Test
    fun `should generate correct diners numbers`() {
        (1..1000)
            .map { generator.generate() }
            .map { number ->
                assertThat(
                    number in firstRange ||
                            number in secondRange ||
                            number in thirdRange
                ).isTrue()
            }
    }
}