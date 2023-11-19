package com.service.infrastructure.repository.creditcard.number

interface CreditCardNumberGenerator {
    fun generate(): Int
}