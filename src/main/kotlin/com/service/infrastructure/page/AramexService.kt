package com.service.infrastructure.page

import com.service.core.domain.ProcessResult
import com.service.core.domain.creditcard.CreditCard
import com.service.core.domain.service.ScamWebsiteService
import com.service.infrastructure.Services
import com.service.infrastructure.repository.creditcard.CreditCardRepository
import com.service.infrastructure.page.command.ActionCommand

private val logger = Services.logger

class AramexService(
    private val creditCardRepository: CreditCardRepository,
    private val navigateToPage: ActionCommand,
): ScamWebsiteService {
    override fun completeForm(): ProcessResult {
        val card = creditCardRepository.getRandomCreditCard()
        logger.log(card.toString())

        val result: Boolean = try {
            navigateToPage.execute(card)
        } catch (ex: Exception) {
            logger.log(ex.message.toString())
            false
        }

        return ProcessResult(
            card,
            result,
        )
    }
}