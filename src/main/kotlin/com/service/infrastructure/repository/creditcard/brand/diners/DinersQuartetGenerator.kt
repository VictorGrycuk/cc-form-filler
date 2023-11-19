package com.service.infrastructure.repository.creditcard.brand.diners

import com.service.infrastructure.repository.creditcard.getRandomInt
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberGenerator

class DinersQuartetGenerator: CreditCardNumberGenerator {
    // Diners Club cards typically start with the digits 300-305, 36, or 38.
    override fun generate() =
        listOf(
            getRandomInt(3000, 3059),
            getRandomInt(3600, 3699),
            getRandomInt(3800, 3899),
        ).random()
}