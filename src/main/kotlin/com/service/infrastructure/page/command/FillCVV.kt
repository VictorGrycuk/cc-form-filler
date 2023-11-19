package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.Services

private val logger = Services.logger

class FillCVV(
    private val page: Page,
    private val selector: String,
    private val blackListedBrands: List<CreditCardBrand> = emptyList(), // Some sites have a CVV field that is not required
    private val nextCommand: ActionCommand? = null,
) : ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        if (!blackListedBrands.contains(card.brand)) {
            logger.log("filling $selector with ${card.cvv}")
            page.fill(selector, card.cvv)
        }
        return nextCommand?.execute(card) ?: true
    }
}