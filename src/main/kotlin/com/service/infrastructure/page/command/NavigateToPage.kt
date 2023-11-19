package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import com.service.infrastructure.Services

private val logger = Services.logger

class NavigateToPage(
    private val page: Page,
    private val url: String,
    private val nextCommand: ActionCommand? = null,
) : ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        logger.log("Navigating to $url")
        page.navigate(url)
        return nextCommand?.execute(card) ?: true
    }
}