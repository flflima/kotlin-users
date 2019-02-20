package br.com.dev.users.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

class User {
    var id: String = ""
    var name: String = ""
    var email: String = ""
    var password: String = ""

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    var created: Date = Date()

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    var modified: Date = Date()

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    var lastLogin: Date = Date()

    var token: String = ""
    var phones: List<Phone> = mutableListOf()

    constructor() : super() {}

    constructor(email: String, password: String) : super() {
        this.email = email
        this.password = password
    }
}