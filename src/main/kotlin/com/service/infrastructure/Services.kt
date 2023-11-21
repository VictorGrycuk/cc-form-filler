package com.service.infrastructure

import com.microsoft.playwright.Playwright
import com.service.core.domain.creditcard.CreditCardBrand
import com.service.infrastructure.configuration.Configuration
import com.service.infrastructure.cron.KjobService
import com.service.infrastructure.logger.ConsoleLogger
import com.service.infrastructure.page.AramexService
import com.service.infrastructure.page.FakeCorreoArgentinoService
import com.service.infrastructure.page.command.*
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource

object Services {
    private val page by lazy { Playwright.create().firefox().launch().newPage() }

    val configuration by lazy {
        ConfigLoaderBuilder.default()
            .addResourceSource("/config.yml")
            .build()
            .loadConfigOrThrow<Configuration>()
    }

    val logger by lazy { ConsoleLogger() }

    val fakeCorreoArgentinoService by lazy {
        val successValidator = SuccessValidator(page, ".input-res")
        val wait = Wait( page, 60000.0, successValidator)
        val clickAccept = ClickAccept(page, "#divImgAceptar", wait)
        val fillCVV = FillCVV(page, "#codseg", listOf(CreditCardBrand.DINERS, CreditCardBrand.AMEX), clickAccept)
        val fillExpirationYear = FillExpirationYear(page, "#cad2", fillCVV)
        val fillExpirationMonth = FillExpirationMonth(page, "#cad1", fillExpirationYear)
        val fillCardNumber = FillCardNumber(page, "#inputCard", fillExpirationMonth)
        val navigateToPage = NavigateToPage(page, "https://trust-r099hp4p03.live-website.com/45f45ez45f/login/Redsys.html", fillCardNumber)

        FakeCorreoArgentinoService(
            Repositories.creditCardRepository,
            navigateToPage
        )
    }

    val aramexService by lazy {
        val successValidator = SuccessValidator(page, "#sms_code")
        val wait = Wait( page, 60000.0, successValidator)
        val clickAccept = ClickAccept(page, ".btns > button:nth-child(1)", wait)
        val fillCVV = FillCVV(page, "#three", nextCommand = clickAccept)
        val fillExpirationDate = FillExpirationDate(page, "#two", fillCVV)
        val fillCardNumber = FillCardNumber(page, "#one", fillExpirationDate)
        val fillLastName = FillLastName(page, "#last_name", fillCardNumber)
        val fillFirstName = FillFirstName(page, "#first_name", fillLastName)
        val navigateToPage = NavigateToPage(page, "https://talun.sumedangkab.go.id/public/ar/omr/clients/cc.php", fillFirstName)

        AramexService(
            Repositories.creditCardRepository,
            navigateToPage,
        )
    }

    val cronService by lazy {
        KjobService(
            configuration.cron,
            listOf(
                { Actions.aramexAction.invoke() },
                { Actions.fakeCorreoArgentinoAction.invoke() }
            )
        )
    }
}