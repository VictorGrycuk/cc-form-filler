package com.service.infrastructure.repository.creditcard.brand.discover

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class DiscoverQuartetGeneratorTest {
    private val generator = DiscoverQuartetGenerator()
    private val quartet = 6011
    private val firstRange = 6221..6229
    private val secondRange = 6440..6499
    private val thirdRange = 6500..6599

    @Test
    fun `should generate correct discover numbers`() {
        (1..1000)
            .map { generator.generate() }
            .map { number ->
                assertThat(
                    number == quartet ||
                            number in firstRange ||
                            number in secondRange ||
                            number in thirdRange
                ).isTrue()
            }
    }
}