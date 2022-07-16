package com.inkapplications.telegram.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands
import kotlin.system.exitProcess

private class MainCommand: NoOpCliktCommand() {
    init {
        subcommands(WebhookInfoCommand, WebhookSetCommand, DebugServerCommand)
    }
}

fun main(args: Array<String>) {
    MainCommand().main(args)
    exitProcess(0)
}
