package com.inkapplications.telegram.client

import com.inkapplications.telegram.structures.Response
import com.inkapplications.telegram.structures.TelegramException
import com.inkapplications.telegram.structures.WebhookInfo
import com.inkapplications.telegram.structures.WebhookParameters
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val API_DOMAIN = "api.telegram.org"

internal class KtorTelegramClient(
    private val token: String,
): TelegramBotClient {
    private val client = HttpClient(KtorPlatformModule.engine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun setWebhook(parameters: WebhookParameters) {
        client.post {
            telegramEndpoint("setWebhook")
            jsonBody(parameters)
        }.responseOrThrow<Boolean>()
    }

    override suspend fun getWebhookInfo(): WebhookInfo {
        return client.get {
            telegramEndpoint("getWebhookInfo")
        }.responseOrThrow()
    }

    private fun HttpRequestBuilder.telegramEndpoint(vararg path: String) {
        url {
            protocol = URLProtocol.HTTPS
            host = API_DOMAIN
            encodedPathSegments = listOf(token, *path)
        }
    }
    private inline fun <reified T> HttpRequestBuilder.jsonBody(body: T) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }

    private suspend inline fun <reified T> HttpResponse.responseOrThrow(): T {
        return try {
            val response = body<Response<T>>()

            when (response) {
                is Response.Result -> response.data
                is Response.Error -> throw TelegramException.External(response)
                else -> throw TelegramException.Internal("Unknown response type ${response::class.simpleName}>")
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            throw TelegramException.Internal("Unable to handle response with status ${status.value}")
        }
    }
}
