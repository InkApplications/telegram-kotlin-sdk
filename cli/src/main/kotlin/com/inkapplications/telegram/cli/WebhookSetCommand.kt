package com.inkapplications.telegram.cli

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.inkapplications.telegram.structures.InputFile
import com.inkapplications.telegram.structures.Update
import com.inkapplications.telegram.structures.WebhookParameters

object WebhookSetCommand: ClientCommand(
    name = "webhook-set",
    help = "Specify a URL and receive incoming updates via an outgoing webhook."
) {
    private val url by argument(
        help = "HTTPS URL to send updates to. Use an empty string to remove webhook integration"
    )
    private val certificateUrl by option(
        help = "URL to upload your public key certificate so that the root certificate in use can be checked."
    )
    private val certificateFileId by option(
        help = "Uploaded file ID of your public key certificate so that the root certificate in use can be checked."
    )
    private val ipAddress by option(
        help = "The fixed IP address which will be used to send webhook requests instead of the IP address resolved through DNS"
    )
    private val maxConnections by option(
        help = "The maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-10"
    ).int()
    private val allowedUpdates by option(
        help = "A List of the update types you want your bot to receive."
    ).multiple()
    private val dropPendingUpdates by option(
        help = "Pass to drop all pending updates"
    ).flag()
    private val secretToken by option(
        help = "A secret token to be sent in a header “X-Telegram-Bot-Api-Secret-Token” in every webhook request, 1-256 characters."
    )

    override suspend fun runCommand() {
        client.setWebhook(WebhookParameters(
            url = url,
            certificate = certificateFileId?.let { InputFile.FileId(it) }
                ?: certificateUrl?.let { InputFile.Url(it) },
            ipAddress = ipAddress,
            maxConnections = maxConnections,
            allowedUpdates = allowedUpdates.map { Update.valueOf(it) },
            dropPendingUpdates = dropPendingUpdates,
            secretToken = secretToken,
        )
        )
    }
}
