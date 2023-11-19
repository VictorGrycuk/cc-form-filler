package com.service.infrastructure.repository.creditcard.number

import com.service.infrastructure.repository.creditcard.CheckDigitGenerator

data class CreditCardNumberBuilder(
    val firstQuartetBuilderGenerator: CreditCardNumberGenerator,
    val secondQuartetBuilderGenerator: CreditCardNumberGenerator,
    val thirdQuartetBuilderGenerator: CreditCardNumberGenerator,
    val fourthQuartetGenerator: CreditCardNumberGenerator,
) {
    fun build(): String {
        val firstQuartet = firstQuartetBuilderGenerator.generate().toString().padStart(4, '0')
        val secondQuartet = secondQuartetBuilderGenerator.generate().toString().padStart(4, '0')
        val thirdQuartet = thirdQuartetBuilderGenerator.generate().toString().padStart(4, '0')
        val fourthQuartet = fourthQuartetGenerator.generate().toString().padStart(3, '0')
        val checkDigit = CheckDigitGenerator.getCheckDigit(firstQuartet + secondQuartet + thirdQuartet + fourthQuartet)

        return "$firstQuartet$secondQuartet$thirdQuartet$fourthQuartet$checkDigit"
    }
}