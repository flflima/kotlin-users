package main.br.com.dev.users.dao

import br.com.dev.users.model.User

class UserDAO {

    var users = mutableMapOf<Int, User>()
    var lastId = 1

    fun save(user: User) {
        user.id = lastId.toString()
        users[lastId++] = user
    }
}