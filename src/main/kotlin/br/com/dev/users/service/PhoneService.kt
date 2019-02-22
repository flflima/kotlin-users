package main.br.com.dev.users.service

import br.com.dev.users.model.Phone
import main.br.com.dev.users.dao.PhoneDAO

class PhoneService {

    var phoneDAO = PhoneDAO()

    fun save(phones: List<Phone>) {
        phones.forEach { phoneDAO.save(it) }
    }

}