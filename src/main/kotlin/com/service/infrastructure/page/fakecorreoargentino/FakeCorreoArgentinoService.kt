package com.service.infrastructure.page.fakecorreoargentino

import com.service.core.domain.ProcessResult
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.core.domain.service.ScamWebsiteService
import com.service.infrastructure.Services
import com.service.infrastructure.repository.creditcard.CreditCardRepository

private val logger = Services.logger

class FakeCorreoArgentinoService(
    private val creditCardRepository: CreditCardRepository,
    private val page: FakeCorreoArgentinoPage,
): ScamWebsiteService {
    override fun completeForm(): ProcessResult {
        val card = creditCardRepository.getRandomCreditCard()
        logger.log(card.toString())

        return try {
            page.navigateTo()
            page.fillCardNumber(card.number)
            page.fillExpirationMonth(card.expiration.getMonth())
            page.fillExpirationYear(card.expiration.getYear())
            // Some sites don't ask for CVV for certain card brands
            if (card.brand !in listOf(CreditCardBrand.DINERS, CreditCardBrand.AMEX))
                page.fillCVV(card.cvv)
            page.clickAccept()
            val result = page.waitForSMSInput()

            ProcessResult(card, result)
        }
        catch (ex: Exception) {
            logger.log(ex.message.toString())
            ProcessResult(card, false)
        }
    }
}