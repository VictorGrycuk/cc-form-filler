package com.service.infrastructure.cron

import com.service.core.domain.ProcessResult
import com.service.core.domain.service.CronService
import com.service.infrastructure.repository.creditcard.getRandomInt
import it.sauronsoftware.cron4j.Scheduler;
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class KjobService(
    private val actions: List<() -> ProcessResult>
): CronService {
    private val maxDelay = 180
    private val scheduler = Scheduler()

    override suspend fun start() {
        // At minute 0 past every 3rd hour.
        scheduler.schedule("0 */3 * * *") {
            runBlocking {

                // Random delay between 1 and 180 minutes to add unpredictability
                delay(getRandomDelay())
                val result = actions.random().invoke()
                println("Result: ${result.success}" )
            }
        }
        // Starts the scheduler.
        scheduler.start()
    }

    private fun getRandomDelay() = getRandomInt(1, maxDelay).toLong() * 60 * 1000
}