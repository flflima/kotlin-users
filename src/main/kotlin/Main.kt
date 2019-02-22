package main

import br.com.dev.users.controller.UserController
import br.com.dev.users.model.User
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.javalin.Javalin
import main.br.com.dev.users.model.Message
import java.lang.IllegalArgumentException

// Main.kt
fun main() {
    JavalinApp(8000).init()
}

class JavalinApp(private val port: Int) {

    fun init(): Javalin {
        println("-----------------------------------------")

        val controller = UserController()

        val app = Javalin.create().apply {
            port(port)
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        }.start()

        app.routes {
            app.post("/users") { ctx ->
                val user = ctx.body<User>()
                controller.createUser(user)
                ctx.status(201)
                ctx.json(user)
            }
        }

        app.exception(IllegalArgumentException::class.java) {
            e, ctx -> ctx.status(500)
            ctx.json(Message(e.message.toString()))
        }

        return app
    }
}

inline fun <reified T : Any> String.deserialize(): T =
    jacksonObjectMapper().readValue(this)