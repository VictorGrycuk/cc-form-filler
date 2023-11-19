package com.service.infrastructure

import com.service.infrastructure.repository.creditcard.number.CreditCardNumberBuilder
import com.service.infrastructure.repository.creditcard.CreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.amex.AmexCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.amex.AmexQuartetGenerator
import com.service.infrastructure.repository.creditcard.brand.diners.DinersCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.diners.DinersQuartetGenerator
import com.service.infrastructure.repository.creditcard.brand.discover.DiscoverCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.discover.DiscoverQuartetGenerator
import com.service.infrastructure.repository.creditcard.brand.mastercard.MastercardCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.mastercard.MastercardQuartetGenerator
import com.service.infrastructure.repository.creditcard.expiration.ExpirationDateGenerator
import com.service.infrastructure.repository.creditcard.number.QuartetGenerator
import com.service.infrastructure.repository.creditcard.brand.visa.VisaCreditCardRepository
import com.service.infrastructure.repository.creditcard.brand.visa.VisaQuartetGenerator

object Repositories {

    private val visaCreditCardRepository by lazy {
        val creditCardNumberBuilder = CreditCardNumberBuilder(
            VisaQuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(max = 999)
        )

        VisaCreditCardRepository(
            creditCardNumberBuilder,
            ExpirationDateGenerator()
        )
    }

    private val mastercardCreditCardRepository by lazy {
        val creditCardNumberBuilder = CreditCardNumberBuilder(
            MastercardQuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(max = 999)
        )

        MastercardCreditCardRepository(
            creditCardNumberBuilder,
            ExpirationDateGenerator()
        )
    }

    private val amexCreditCardRepository by lazy {
        // This has to be fixed as it is not working as intended
        val creditCardNumberBuilder = CreditCardNumberBuilder(
            AmexQuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(max = 999) // Amex number has 15 digits
        )

        AmexCreditCardRepository(
            creditCardNumberBuilder,
            ExpirationDateGenerator()
        )
    }

    private val dinersCreditCardRepository by lazy {
        // This has to be fixed as it is not working as intended
        val creditCardNumberBuilder = CreditCardNumberBuilder(
            DinersQuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(max = 999)
        )

        DinersCreditCardRepository(
            creditCardNumberBuilder,
            ExpirationDateGenerator()
        )
    }

    private val discoverCreditCardRepository by lazy {
        val creditCardNumberBuilder = CreditCardNumberBuilder(
            DiscoverQuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(),
            QuartetGenerator(max = 999)
        )

        DiscoverCreditCardRepository(
            creditCardNumberBuilder,
            ExpirationDateGenerator()
        )
    }

    val creditCardRepository by lazy {
        CreditCardRepository(
            visaCreditCardRepository,
            mastercardCreditCardRepository,
            amexCreditCardRepository,
            dinersCreditCardRepository,
            discoverCreditCardRepository,
        )
    }
}