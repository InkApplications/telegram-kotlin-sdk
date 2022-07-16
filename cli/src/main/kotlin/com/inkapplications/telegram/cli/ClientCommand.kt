package com.inkapplications.telegram.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.inkapplications.telegram.client.TelegramBotClient
import com.inkapplications.telegram.client.TelegramClientModule
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class ClientCommand(
    name: String? = null,
    help: String = ""
): CliktCommand(
    name = name,
    help = help,
) {
    private val token by option(
        envvar = "TELEGRAM_TOKEN",
        help = "Bot API token (optional, if TELEGRAM_TOKEN env variable is set)"
    )
    private val clientModule = TelegramClientModule()
    protected val prettyJson = Json { prettyPrint = true }
    protected val client: TelegramBotClient by lazy {
        clientModule.createClient(token ?: error("Missing TELEGRAM_TOKEN. Specify as an environment variable or with `--token` parameter"))
    }

    final override fun run() {
        runBlocking {
            runCommand()
        }
    }

    inline fun <reified T> Json.print(data: T) = encodeToString(data).let(::println)

    abstract suspend fun runCommand()
}
