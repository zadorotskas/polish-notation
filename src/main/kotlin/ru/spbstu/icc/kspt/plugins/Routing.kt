package ru.spbstu.icc.kspt.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.calculation.Calculator
import ru.spbstu.icc.kspt.converters.ConverterFromPolishImpl
import ru.spbstu.icc.kspt.converters.ConverterToReversePolishImpl
import ru.spbstu.icc.kspt.validation.ValidatorInfixImpl
import ru.spbstu.icc.kspt.validation.ValidatorPolishImpl

const val INFIX = "infix"
const val POLISH = "polish"
const val CALCULATE = "calculate"

fun Application.configureRouting() {
    routing {
        post("/api/toPolish") {
            val infixNotation = call.parameters[INFIX]!!
            if (!ValidatorInfixImpl.isValid(infixNotation)) {
                call.respondText("Invalid expression, cannot convert", status = HttpStatusCode.BadRequest)
                return@post
            }

            var result = ConverterToReversePolishImpl.convert(infixNotation)
            if (call.parameters[CALCULATE] == "true") {
               result += " = ${result.calculate()}"
            }

            call.respondText(result)
        }

        post("/api/fromPolish") {
            val polishNotation = call.parameters[POLISH]!!
            if (!ValidatorPolishImpl.isValid(polishNotation)) {
                call.respondText("Invalid expression, cannot convert", status = HttpStatusCode.BadRequest)
                return@post
            }

            var result = ConverterFromPolishImpl.convert(polishNotation)
            if (call.parameters[CALCULATE] == "true") {
                result += " = ${polishNotation.calculate()}"
            }

            call.respondText(result)
        }
    }
}

private fun String.calculate(): Int {
    return Calculator.calculatePolishExpression(this.split(" "))
}
