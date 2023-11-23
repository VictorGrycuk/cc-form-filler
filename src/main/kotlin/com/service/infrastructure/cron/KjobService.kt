package com.service.infrastructure.cron

import com.service.core.domain.ProcessResult
import com.service.core.domain.service.CronService
import com.service.infrastructure.configuration.Cron
import com.service.infrastructure.repository.creditcard.getRandomInt
import it.sauronsoftware.cron4j.Scheduler;
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class KjobService(
    private val configuration: Cron,
    private val actions: List<() -> ProcessResult>
): CronService {
    private val maxDelay = configuration.maxDelay
    private val scheduler = Scheduler()

    override suspend fun start() {
        scheduler.schedule(configuration.pattern) {
            runBlocking {

                // Random delay between 1 and 180 minutes to add unpredictability
                delay(getRandomDelay())
                val result = actions.random().invoke()
                println("Result: ${result.success}" )
            }
        }

        scheduler.start()
    }

    private fun getRandomDelay() = getRandomInt(1, maxDelay).toLong() * 60 * 1000
}