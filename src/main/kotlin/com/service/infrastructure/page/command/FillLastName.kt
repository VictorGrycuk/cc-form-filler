package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Services

private val logger = Services.logger

class FillLastName(
    private val page: Page,
    private val selector: String,
    private val nextCommand: ActionCommand? = null,
) : ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        logger.log("Filling $selector with ${card.holder.lastName}")
        page.fill(selector, card.holder.lastName)
        return nextCommand?.execute(card) ?: true
    }
}