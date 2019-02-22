package main.br.com.dev.users.service

import br.com.dev.users.model.User
import main.br.com.dev.users.dao.UserDAO

class UserService {

    var userDAO: UserDAO = UserDAO()

    fun save(user: User) {
        userDAO.save(user)
    }

    fun emailExists(user: User): Boolean {
        return userDAO.users.filter {
            it.value.email == user.email
        }.count() > 0
    }

    fun getAllUsers(): MutableMap<Int, User> {
        return userDAO.users
    }

}