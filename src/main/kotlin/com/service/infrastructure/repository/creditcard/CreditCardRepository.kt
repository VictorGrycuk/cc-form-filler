package com.service.infrastructure.repository.creditcard

import com.service.core.domain.creditcard.CreditCard
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.repository.creditcard.brand.amex.AmexCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.diners.DinersCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.discover.DiscoverCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.mastercard.MastercardCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.visa.VisaCreditCardRepository

class CreditCardRepository(
    private val visaCreditCardRepository: VisaCreditCardRepository,
    private val mastercardCreditCardRepository: MastercardCreditCardRepository,
    private val amexCreditCardRepository: AmexCreditCardRepository,
    private val dinersCreditCardRepository: DinersCreditCardRepository,
    private val discoverCreditCardRepository: DiscoverCreditCardRepository,
) {
    fun getRandomCreditCard() = getCreditCard(CreditCardBrand.values().random())

    fun getCreditCard(brand: CreditCardBrand): CreditCard {
        return when(brand) {
            CreditCardBrand.VISA -> visaCreditCardRepository.getRandom()
            CreditCardBrand.MASTERCARD -> mastercardCreditCardRepository.getRandom()
            CreditCardBrand.AMEX -> amexCreditCardRepository.getRandom()
            CreditCardBrand.DINERS -> dinersCreditCardRepository.getRandom()
            CreditCardBrand.DISCOVER -> discoverCreditCardRepository.getRandom()
        }
    }
}