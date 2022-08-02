package com.example.routes

import com.example.models.Order
import com.example.repository.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRouting(
    db: DatabaseFactory
) {
    route("/order"){
        get {
            val orderList=db.getAllOrders()
            call.respond(orderList)
        }
        post {
            val order = call.receive<Order>()
            val storedOrder=db.addOrder(order)
            call.respond(storedOrder)
        }
    }
}