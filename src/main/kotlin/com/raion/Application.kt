package com.raion

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.raion.plugins.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        install(CORS) {
            anyHost()
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Options)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            header(HttpHeaders.ContentType)
        }
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
