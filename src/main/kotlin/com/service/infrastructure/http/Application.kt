package com.service.infrastructure.http

import com.service.infrastructure.Services
import kotlinx.coroutines.runBlocking

class Application {
    companion object {
        @JvmStatic
        fun main(args : Array<String>) {
            runBlocking {
                Services.cronService.start()
            }
        }
    }
}