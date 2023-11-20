package com.service.infrastructure.http

import com.service.infrastructure.Services

suspend fun main() {
    Services.cronService.start()
}