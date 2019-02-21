package br.com.dev.users.integration

import br.com.dev.users.model.User
import io.javalin.Javalin
import main.JavalinApp
import main.deserialize
import org.json.JSONObject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TestIntegration {

    private val logger = LoggerFactory.getLogger(TestIntegration::class.java)

    private val url = "http://localhost:8000/users"

    //    companion object {
    private lateinit var app: Javalin

    @BeforeEach
//        @JvmStatic
    fun setUp() {
        app = JavalinApp(8000).init()
    }

    @AfterEach
//        @JvmStatic
    fun tearDown() {
        app.stop()
    }
//    }

    @Test
    fun `create user then return success code`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        val response = khttp.post(url = url, data = JSONObject(user))

        assertEquals(201, response.statusCode)
    }

    @Test
    fun `create user the id greater then zero`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        val response = khttp.post(url = url, data = JSONObject(user))
        val userCad = response.text.deserialize<User>()
        assert(userCad.id.toInt() > 0)

        val user2 = User("fulano.de.tal@email.com", "123456789")
        val response2 = khttp.post(url = url, data = JSONObject(user2))
        val userCad2 = response2.text.deserialize<User>()
        assert(userCad2.id.toInt() > 1)
    }
}