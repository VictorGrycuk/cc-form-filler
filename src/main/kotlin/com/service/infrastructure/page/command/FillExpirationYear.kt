package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Services

private val logger = Services.logger

class FillExpirationYear(
    private val page: Page,
    private val selector: String,
    private val nextCommand: ActionCommand? = null,
) : ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        logger.log("Filling $selector with ${card.expiration.getYear()}")
        page.fill(selector, card.expiration.getYear())
        return nextCommand?.execute(card) ?: true
    }
}