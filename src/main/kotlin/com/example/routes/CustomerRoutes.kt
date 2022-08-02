package com.example.routes

import com.example.models.Customer
import com.example.repository.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting(
    db:DatabaseFactory
) {
    route("/customer"){
        get {
            val customer=db.getAllCustomer()
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            val storedCustomer=db.addCustomer(customer)
            call.respond(storedCustomer)
        }
    }

}