package com.service.infrastructure.http

import com.service.infrastructure.http.provider.KtorProvider

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        KtorProvider.start()
    }
}