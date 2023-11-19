package com.service.core.domain.creditcard

data class CreditCard(
    val number: String,
    val brand: CreditCardBrand,
    val cvv: String,
    val expiration: CreditCardExpiration,
    val holder: CardHolder
)