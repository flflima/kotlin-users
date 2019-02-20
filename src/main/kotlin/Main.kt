package main

import br.com.dev.users.controller.UserController
import br.com.dev.users.model.User
import io.javalin.Javalin

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
                println("-----------------------------------------")
                println(user.email)
                controller.createUser(user)
                ctx.status(201)
            }
        }

        return app
    }
}