package ru.spbstu.icc.kspt

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.plugins.configureRouting

class ApplicationTest {

    @Test
    fun `toPolish - happy path`() = testApplication {
        //given
        application {
            configureRouting()
        }
        val infix = "2 %2B 4 * 3 - 10 / 5"

        //when
        val response = client.post("/api/toPolish?infix=$infix")

        //then
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("2 4 3 * + 10 5 / -", response.bodyAsText())
    }

    @Test
    fun `toPolish should calculate espression`() = testApplication {
        //given
        application {
            configureRouting()
        }
        val infix = "2 %2B 4 * 3 - 10 / 5"

        //when
        val response = client.post("/api/toPolish?infix=$infix&calculate=true")

        //then
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("2 4 3 * + 10 5 / - = 12", response.bodyAsText())
    }

    @Test
    fun `fromPolish - happy path`() = testApplication {
        //given
        application {
            configureRouting()
        }
        val polish = "2+4+3+*+%2B+10+5+/+-"

        //when
        val response = client.post("/api/fromPolish?polish=$polish")

        //then
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("((2+(4*3))-(10/5))", response.bodyAsText())
    }

    @Test
    fun `fromPolish should return error message when expression is invalid`() = testApplication {
        //given
        application {
            configureRouting()
        }
        val polish = "2 4 3 * + 10 5 / -"

        //when
        val response = client.post("/api/fromPolish?polish=$polish")

        //then
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("Invalid expression, cannot convert", response.bodyAsText())
    }
}