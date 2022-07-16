package com.inkapplications.telegram.cli

object WebhookInfoCommand: ClientCommand(
    name = "webhook-info",
    help = "Get current webhook status."
) {
    override suspend fun runCommand() {
        prettyJson.print(client.getWebhookInfo())
    }
}
