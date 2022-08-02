package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.repository.DatabaseFactory

fun main() {
    val db=DatabaseFactory()
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureSerialization()
        configureRouting(db)
    }.start(wait = true)
}
