package com.raion.plugins

import com.raion.route.BerhasilIdRoute
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Application.configureRouting() {
    val route = BerhasilIdRoute()

    install(Locations) {
    }

    routing {
        get("/"){
            call.respondText("Hello Ktor!")
        }

        route.apply { initAllRoutes() }
    }
}

