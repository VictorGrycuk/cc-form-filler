package com.service.infrastructure.http.provider

import cc.rbbl.ktor_health_check.Health
import com.service.core.action.AramexAction
import com.service.core.action.FakeCorreoArgentinoAction
import com.service.infrastructure.Actions
import com.service.infrastructure.http.Path
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object KtorProvider {
    fun start() {
        val server = embeddedServer(Netty, port = 8080) {
            install(ContentNegotiation) {
                jackson()
            }
            install(Health)
            routing {
                fakeCorreoArgentino(Actions.fakeCorreoArgentinoAction)
                aramex(Actions.aramexAction)
            }
        }
        server.start(wait = true)
    }

    private fun Route.fakeCorreoArgentino(fakeCorreoArgentinoAction: FakeCorreoArgentinoAction) {
        post(Path.FAKE_CORREO_ARGENTINO) {
            val result = fakeCorreoArgentinoAction()
            call.respond(HttpStatusCode.OK, result)
        }
    }

    private fun Route.aramex(aramexAction: AramexAction) {
        post(Path.ARAMEX) {
            val result = aramexAction()
            call.respond(HttpStatusCode.OK, result)
        }
    }
}