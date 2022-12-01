package ru.spbstu.icc.kspt.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.converters.ConverterFromPolishImpl
import ru.spbstu.icc.kspt.converters.ConverterToReversePolishImpl

const val INFIX = "infix"
const val POLISH = "polish"

fun Application.configureRouting() {
    routing {
        post("/api/toPolish") {
            val infixNotation = call.parameters[INFIX]!!
            val result = ConverterToReversePolishImpl.convert(infixNotation)
            call.respondText(result)
        }

        post("/api/fromPolish") {
            val infixNotation = call.parameters[POLISH]!!
            val result = ConverterFromPolishImpl.convert(infixNotation)
            call.respondText(result)
        }
    }
}
