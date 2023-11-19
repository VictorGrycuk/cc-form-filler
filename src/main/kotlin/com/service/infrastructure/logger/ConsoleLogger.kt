package com.service.infrastructure.logger

import com.service.core.domain.logging.Logger

class ConsoleLogger: Logger {
    override fun log(message: String) {
        println(message)
    }
}