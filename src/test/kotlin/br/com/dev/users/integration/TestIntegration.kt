package br.com.dev.users.integration

import br.com.dev.users.model.Phone
import br.com.dev.users.model.User
import io.javalin.Javalin
import main.JavalinApp
import main.br.com.dev.users.model.Message
import main.deserialize
import org.json.JSONObject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TestIntegration {

    private val logger = LoggerFactory.getLogger(TestIntegration::class.java)

    private val url = "http://localhost:8000/users"

    private lateinit var app: Javalin

    @BeforeEach
    fun setUp() {
        app = JavalinApp(8000).init()
    }

    @AfterEach
    fun tearDown() {
        app.stop()
    }

    @Test
    fun `create user then return success code`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        val response = khttp.post(url = url, data = JSONObject(user))

        assertEquals(201, response.statusCode)
    }

    @Test
    fun `create user then id greater than zero`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        val response = khttp.post(url = url, data = JSONObject(user))
        val userCad = response.text.deserialize<User>()
        assert(userCad.id.toInt() > 0)

        val user2 = User("sicrano.de.tal@email.com", "123456789")
        val response2 = khttp.post(url = url, data = JSONObject(user2))
        val userCad2 = response2.text.deserialize<User>()
        assert(userCad2.id.toInt() > 1)
    }

    @Test
    fun `when create user with phone list then phone id is greater than zero`() {
        val user = User("fulano.de.tal@email.com", "123456789")
        user.phones = listOf(Phone("13", "99999-9999"), Phone("13", "99999-9999"))
        val response = khttp.post(url = url, data = JSONObject(user))
        val userCad = response.text.deserialize<User>()

        assertTrue(userCad.phones[0].id.toInt() > 0)
        assertTrue(userCad.phones[1].id.toInt() > 0)
    }

    @Test
    fun `when email already exists then returns error`() {
        val user1 = User("fulano.de.tal@email.com", "123456789")
        khttp.post(url = url, data = JSONObject(user1))

        val user2 = User("fulano.de.tal@email.com", "123456789")
        val response2 = khttp.post(url = url, data = JSONObject(user2))
        assertEquals(500, response2.statusCode)
    }

    @Test
    fun `when email already exists then returns error message`() {
        val user1 = User("fulano.de.tal@email.com", "123456789")
        khttp.post(url = url, data = JSONObject(user1))

        val user2 = User("fulano.de.tal@email.com", "123456789")
        val response2 = khttp.post(url = url, data = JSONObject(user2))

        val responseMessage = response2.text.deserialize<Message>()

        assertEquals("E-mail já existente", responseMessage.mensagem)
    }
}