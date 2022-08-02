package com.example.routes

import com.example.models.Food
import com.example.repository.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.foodRouting(db:DatabaseFactory) {
    route("/food") {
        get {
            val foodList=db.getAllFood()
            call.respond(foodList)
        }
        post {
            val food = call.receive<Food>()
            val storedFood=db.addFood(food)
            call.respond(storedFood)
        }
    }
}