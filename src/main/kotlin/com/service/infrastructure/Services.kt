package com.service.infrastructure

import com.microsoft.playwright.Playwright
import com.service.infrastructure.configuration.Configuration
import com.service.infrastructure.cron.KjobService
import com.service.infrastructure.logger.ConsoleLogger
import com.service.infrastructure.page.aramex.AramexPage
import com.service.infrastructure.page.aramex.AramexService
import com.service.infrastructure.page.fakecorreoargentino.FakeCorreoArgentinoPage
import com.service.infrastructure.page.fakecorreoargentino.FakeCorreoArgentinoService
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
        FakeCorreoArgentinoService(
            Repositories.creditCardRepository,
            FakeCorreoArgentinoPage(page),
        )
    }

    val aramexService by lazy {
        AramexService(
            Repositories.creditCardRepository,
            AramexPage(page),
        )
    }

    val cronService by lazy {
        KjobService(
            configuration.cron,
            listOf(
                { Actions.aramexAction.invoke() },
                // { Actions.fakeCorreoArgentinoAction.invoke() } Seems to be offline
            )
        )
    }
}