package br.com.dev.users.controller

import br.com.dev.users.model.User
import java.text.SimpleDateFormat
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/users")
class UserController {

    var users = mutableMapOf<Int, User>()
    var lastId = 1

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    fun createUser(user: User) {
        user.id = lastId.toString()
        users[lastId++] = user
    }

}