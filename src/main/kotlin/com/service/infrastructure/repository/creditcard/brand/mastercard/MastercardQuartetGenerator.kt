package com.service.infrastructure.repository.creditcard.brand.mastercard

import com.service.infrastructure.repository.creditcard.getRandomInt
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberGenerator

class MastercardQuartetGenerator: CreditCardNumberGenerator {
    // The first two digits are in the range of 51-55, but are exceptions
    override fun generate() = getRandomInt(5100, 5599)
}