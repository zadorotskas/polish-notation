package ru.spbstu.icc.kspt.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.converters.ConverterFromPolishImpl
import ru.spbstu.icc.kspt.converters.ConverterToReversePolishImpl
import ru.spbstu.icc.kspt.validation.ValidatorInfixImpl
import ru.spbstu.icc.kspt.validation.ValidatorPolishImpl

const val INFIX = "infix"
const val POLISH = "polish"

fun Application.configureRouting() {
    routing {
        post("/api/toPolish") {
            val infixNotation = call.parameters[INFIX]!!
            if (ValidatorInfixImpl.isValid(infixNotation)) {
                val result = ConverterToReversePolishImpl.convert(infixNotation)
                call.respondText(result)
            } else {
                call.respondText("Invalid expression, cannot convert", status = HttpStatusCode.BadRequest)
            }
        }

        post("/api/fromPolish") {
            val polishNotation = call.parameters[POLISH]!!
            if (ValidatorPolishImpl.isValid(polishNotation)) {
                val result = ConverterFromPolishImpl.convert(polishNotation)
                call.respondText(result)
            } else {
                call.respondText("Invalid expression, cannot convert", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
