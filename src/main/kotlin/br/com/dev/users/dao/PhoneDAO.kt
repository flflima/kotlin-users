package main.br.com.dev.users.dao

import br.com.dev.users.model.Phone

class PhoneDAO {
    var lastId = 1

    fun save(phone: Phone) {
        phone.id = lastId.toString()
        lastId++
    }

}
