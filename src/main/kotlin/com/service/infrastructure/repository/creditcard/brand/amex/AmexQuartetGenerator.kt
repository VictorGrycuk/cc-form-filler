package com.service.infrastructure.repository.creditcard.brand.amex

import com.service.infrastructure.repository.creditcard.getRandomInt
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberGenerator

class AmexQuartetGenerator: CreditCardNumberGenerator {
    //  American Express cards start with the digits 34 or 37.
    override fun generate() =
        listOf(
            getRandomInt(3400, 3499),
            getRandomInt(3700, 3799)
        ).random()
}