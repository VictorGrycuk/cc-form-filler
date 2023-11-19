package com.service.infrastructure.repository.creditcard

import com.service.core.domain.creditcard.CreditCard
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.repository.creditcard.expiration.ExpirationDateGenerator
import com.service.infrastructure.repository.creditcard.holder.HolderGenerator
import com.service.infrastructure.repository.creditcard.number.CreditCardNumberBuilder

open class BaseCreditCardRepository(
    private val creditCardNumberBuilder: CreditCardNumberBuilder,
    private val expirationDateGenerator: ExpirationDateGenerator,
    private val creditCardBrand: CreditCardBrand,
    private val cardHolder: HolderGenerator = HolderGenerator(),
) {
    fun getRandom(): CreditCard {
        return CreditCard(
            number = creditCardNumberBuilder.build(),
            brand = creditCardBrand,
            cvv = getRandomInt(0, 999).toString().padStart(3, '0'),
            expiration = expirationDateGenerator.generate(),
            holder = cardHolder.getCardHolder(),
        )
    }
}