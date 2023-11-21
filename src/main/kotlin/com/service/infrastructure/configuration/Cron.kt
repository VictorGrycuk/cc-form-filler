package com.service.infrastructure.configuration

data class Cron(
    val pattern: String,
    val maxDelay: Int,
)