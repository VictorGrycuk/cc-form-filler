package com.service.infrastructure.repository.creditcard.number

import com.service.infrastructure.repository.creditcard.getRandomInt

data class QuartetGenerator(
    private val min: Int = 0,
    private val max: Int = 9999
): CreditCardNumberGenerator {
    override fun generate() = getRandomInt(min, max)
}