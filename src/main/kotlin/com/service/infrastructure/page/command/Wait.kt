package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Services

private val logger = Services.logger

class Wait(
    private val page: Page,
    private val waitFor: Double = 5000.0,
    private val nextCommand: ActionCommand? = null,
): ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        logger.log("Waiting for $waitFor ms")
        page.waitForTimeout(waitFor)
        return nextCommand?.execute(card) ?: true
    }
}