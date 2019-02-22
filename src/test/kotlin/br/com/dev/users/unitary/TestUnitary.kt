package br.com.dev.users.unitary

import br.com.dev.users.model.Phone
import br.com.dev.users.model.User
import main.br.com.dev.users.service.PhoneService
import main.br.com.dev.users.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestUnitary {

    lateinit var userService: UserService
    lateinit var phoneService: PhoneService

    @BeforeEach
    fun setUp() {
        userService = UserService()
        phoneService = PhoneService()
    }

    @Test
    fun `when email already exists then return true`() {
        val user1 = User("fulano.de.tal@email.com", "123456789")
        val user2 = User("fulano.de.tal@email.com", "123456789")

        userService.save(user1)
        userService.save(user2)

        assert(userService.emailExists(user2))
    }

    @Test
    fun `when saving list of telephone ids exists and greater than zero`() {
        val phone1 = Phone("11", "99999999")
        val phone2 = Phone("11", "99999999")

        val phones = listOf(phone1, phone2)
        phoneService.save(phones)

        assert(phones[0].id.toInt() > 0)
        assert(phones[1].id.toInt() > 1)
    }
}