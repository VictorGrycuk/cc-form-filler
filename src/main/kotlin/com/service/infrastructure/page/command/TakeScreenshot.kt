package com.service.infrastructure.page.command

import com.microsoft.playwright.Page
import com.service.core.domain.creditcard.CreditCard
import java.nio.file.Paths

class TakeScreenshot(
    private val page: Page,
    private val nextCommand: ActionCommand? = null,
) : ActionCommand {
    override fun execute(card: CreditCard): Boolean {
        page.screenshot(Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")))
        return nextCommand?.execute(card) ?: true
    }
}