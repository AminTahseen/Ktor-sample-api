package com.example.plugins

import com.example.repository.DatabaseFactory
import com.example.routes.customerRouting
import com.example.routes.foodRouting
import com.example.routes.orderRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting(db:DatabaseFactory) {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting(db)
        foodRouting(db)
        orderRouting(db)
    }
}
