package com.service.infrastructure.page.aramex

import com.microsoft.playwright.Page
import java.nio.file.Paths

class AramexPage(
    private val page: Page
) {
    private val url = "https://talun.sumedangkab.go.id/public/ar/omr/clients/cc.php"
    private val firstNameSelector = "#first_name"
    private val lastNameSelector = "#last_name"
    private val cardNumberSelector = "#one"
    private val expirationDateSelector = "#two"
    private val cvvSelector = "#three"
    private val acceptButtonSelector = ".btns > button:nth-child(1)"
    private val smsCodeSelector = "#sms_code"
    private val timeout = 60000.0

    fun navigateTo() = page.navigate(url)

    fun fillFirstName(firstName: String) = page.fill(firstNameSelector, firstName)

    fun fillLastName(lastName: String) = page.fill(lastNameSelector, lastName)

    fun fillCardNumber(cardNumber: String) = page.fill(cardNumberSelector, cardNumber)

    fun fillExpirationDate(date: String) = page.fill(expirationDateSelector, date)

    fun fillCVV(cvv: String) = page.fill(cvvSelector, cvv)

    fun clickAccept() = page.click(acceptButtonSelector)

    fun waitForSMSInput(): Boolean {
        page.waitForTimeout(timeout)
        return page.isVisible(smsCodeSelector)
    }

    fun takeScreenshot() {
        val options = Page.ScreenshotOptions().setPath(Paths.get("aramex-screenshot.png"))
        page.screenshot(options)
    }
}