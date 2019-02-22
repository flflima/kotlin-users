package br.com.dev.users.unitary

import br.com.dev.users.model.User
import main.JavalinApp
import main.br.com.dev.users.dao.UserDAO
import main.br.com.dev.users.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestUnitary {

    lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService()
    }

    @Test
    fun `when email already exists then return true`() {
        val user1 = User("fulano.de.tal@email.com", "123456789")
        val user2 = User("fulano.de.tal@email.com", "123456789")

        userService.save(user1)
        userService.save(user2)

        assert(userService.emailExists(user2))
    }
}