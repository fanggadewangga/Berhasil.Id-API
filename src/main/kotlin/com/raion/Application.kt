package com.raion

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.raion.plugins.*
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "localhost") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
