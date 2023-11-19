package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Services

private val logger = Services.logger

class CustomText(
    private val page: Page,
    private val selector: String,
    private val text: String,
    private val nextCommand: ActionCommand? = null,
): ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        logger.log("Filling $selector with $text")
        page.fill(selector, text)
        return nextCommand?.execute(card) ?: true
    }
}