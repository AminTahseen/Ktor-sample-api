package com.example.routes

import com.example.models.Customer
import com.example.models.Food
import com.example.models.foodStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.foodRouting() {
    route("/food") {
        get {
            if (foodStorage.isNotEmpty()) {
                call.respond(foodStorage)
            } else {
                call.respondText("No foods found", status = HttpStatusCode.OK)
            }
        }
        post {
            val food = call.receive<Food>()
            foodStorage.add(food)
            call.respondText("Food stored correctly", status = HttpStatusCode.Created)
        }
    }
}