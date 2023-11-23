package com.service.infrastructure.page.fakecorreoargentino

import com.microsoft.playwright.Page
import com.microsoft.playwright.Response
import java.nio.file.Paths

class FakeCorreoArgentinoPage(
    private val page: Page
) {
    private val url = "cd .."
    private val cardNumberSelector = "#inputCard"
    private val expirationMonthSelector = "#cad1"
    private val expirationYearSelector = "#cad2"
    private val cvvSelector = "#codseg"
    private val acceptButtonSelector = "#divImgAceptar"
    private val smsCodeSelector = ".input-res"
    private val timeout = 60000.0

    fun navigateTo(): Response = page.navigate(url)

    fun fillCardNumber(cardNumber: String) = page.fill(cardNumberSelector, cardNumber)

    fun fillExpirationMonth(month: String) = page.fill(expirationMonthSelector, month)

    fun fillExpirationYear(year: String) = page.fill(expirationYearSelector, year)

    fun fillCVV(cvv: String) = page.fill(cvvSelector, cvv)

    fun clickAccept() = page.click(acceptButtonSelector)

    fun waitForSMSInput(): Boolean {
        page.waitForTimeout(timeout)
        return page.isVisible(smsCodeSelector)
    }

    fun takeScreenshot() {
        val options = Page.ScreenshotOptions().setPath(Paths.get("correo-argentino-screenshot.png"))
        page.screenshot(options)
    }
}