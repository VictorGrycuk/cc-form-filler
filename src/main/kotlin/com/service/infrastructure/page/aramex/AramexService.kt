package com.service.infrastructure.page.aramex

import com.service.core.domain.ProcessResult
import com.service.core.domain.service.ScamWebsiteService
import com.service.infrastructure.Services
import com.service.infrastructure.repository.creditcard.CreditCardRepository

private val logger = Services.logger

class AramexService(
    private val creditCardRepository: CreditCardRepository,
    private val page: AramexPage,
): ScamWebsiteService {
    override fun completeForm(): ProcessResult {
        val card = creditCardRepository.getRandomCreditCard()
        logger.log(card.toString())

        return try {
            page.navigateTo()
            page.fillFirstName(card.holder.firstName)
            page.fillLastName(card.holder.lastName)
            page.fillCardNumber(card.number)
            page.fillExpirationDate("${card.expiration.getMonth()}/${card.expiration.getYear()}")
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