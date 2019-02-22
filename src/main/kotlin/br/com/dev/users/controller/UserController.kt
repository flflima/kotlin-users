package br.com.dev.users.controller

import br.com.dev.users.model.User
import main.br.com.dev.users.dao.UserDAO
import main.br.com.dev.users.service.PhoneService
import main.br.com.dev.users.service.UserService
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/users")
class UserController {

    var userService = UserService()
    var phoneService = PhoneService()

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    fun createUser(user: User) {
        if (userService.emailExists(user)) {
            throw IllegalArgumentException("E-mail já existente")
        }

        phoneService.save(user.phones)
        userService.save(user)
    }

}