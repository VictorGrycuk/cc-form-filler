package com.service.infrastructure.repository.creditcard.brand.discover

import com.service.infrastructure.repository.creditcard.getRandomInt
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberGenerator

class DiscoverQuartetGenerator: CreditCardNumberGenerator {
    // Discover cards typically start with the digits 6011, 622126-622925, 644-649, or 65.
    // At the moment the model doesn't support generator numbers above 4 digits. Need a bit of tweaking
    override fun generate() =
        listOf(
            6011,
            getRandomInt(6221, 6229),
            getRandomInt(6440, 6499),
            getRandomInt(6500, 6599),
        ).random()
}