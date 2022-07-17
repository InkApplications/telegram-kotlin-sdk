package com.inkapplications.telegram.client

import com.inkapplications.telegram.structures.Message
import com.inkapplications.telegram.structures.MessageParameters
import com.inkapplications.telegram.structures.WebhookInfo
import com.inkapplications.telegram.structures.WebhookParameters

/**
 * Actions available for a Telegram bot
 */
interface TelegramBotClient {
    /**
     * Use this method to specify a URL and receive incoming updates via
     * an outgoing webhook.
     *
     * Whenever there is an update for the bot, we will send an HTTPS POST
     * request to the specified URL, containing a JSON-serialized Update.
     * In case of an unsuccessful request, we will give up after a reasonable
     * amount of attempts.
     *
     * @param parameters Webhook definition and options.
     */
    suspend fun setWebhook(parameters: WebhookParameters)

    /**
     * Use this method to get current webhook status.
     *
     * If the bot is using getUpdates, will return an object with the url
     * field empty.
     */
    suspend fun getWebhookInfo(): WebhookInfo

    /**
     * Use this method to send text messages.
     *
     * @param parameters Definition for the message to be sent
     */
    suspend fun sendMessage(parameters: MessageParameters): Message
}
