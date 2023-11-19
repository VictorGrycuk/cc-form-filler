package com.service.infrastructure.repository.creditcard.brand.visa

import com.service.infrastructure.repository.creditcard.getRandomInt
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberGenerator

class VisaQuartetGenerator: CreditCardNumberGenerator {
    // The first digit is always a 4. Visa cards start with the number 4.
    override fun generate() = getRandomInt(4000,4999)
}