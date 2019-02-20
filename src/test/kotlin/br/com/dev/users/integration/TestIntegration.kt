package br.com.dev.users.integration

import br.com.dev.users.model.User
import io.javalin.Javalin
import main.JavalinApp
import org.json.JSONObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestIntegration {
    private val url = "http://localhost:8000/users"

    companion object {
        private lateinit var app: Javalin

        @BeforeAll
        @JvmStatic
        fun setUp() {
            app = JavalinApp(8000).init()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            app.stop()
        }
    }

    @Test
    fun `create user then return success code`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        val response = khttp.post(url = url, data = JSONObject(user))

        assertEquals(201, response.statusCode)
    }
}