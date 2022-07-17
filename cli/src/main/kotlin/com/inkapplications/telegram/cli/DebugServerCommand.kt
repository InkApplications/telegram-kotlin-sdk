package com.inkapplications.telegram.cli

import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.inkapplications.telegram.structures.MessageParameters
import com.inkapplications.telegram.structures.Update
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

object DebugServerCommand: ClientCommand(
    help = "Run a rudimentary server to log received calls."
) {
    private val port by option(
        help = "Port to start webserver on"
    ).int().default(8080)

    override suspend fun runCommand() {
        embeddedServer(Netty, port = port) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            routing {
                route("/") {
                    get {
                        echo("[GET]: /")
                        call.respondText("OK")
                    }
                    post {
                        echo("[POST]: /")
                        try {
                            val update = call.receive<Update>()
                            when (update) {
                                is Update.MessageUpdate -> {
                                    echo("[Message]: ${update.message}")
                                    client.sendMessage(MessageParameters(
                                        chatId = update.message.chat.id.toString(),
                                        text = "Hello World!"
                                    ).also { println(it) })
                                }
                                is Update.EditedMessageUpdate -> echo("[Message Edit]: ${update.message}")
                                else -> echo("[Update]: $update")
                            }
                        } catch (e: Throwable) {
                            echo("[BODY]: ${call.receiveText()}")
                        }
                        call.respondText("OK")
                    }
                }
            }
        }.start(wait = true)
    }
}
