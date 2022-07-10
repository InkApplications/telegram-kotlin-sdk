package com.inkapplications.telegram.client

import com.inkapplications.telegram.structures.Response
import com.inkapplications.telegram.structures.TelegramException
import com.inkapplications.telegram.structures.WebhookParameters
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
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
        val response = client.post {
            url {
                protocol = URLProtocol.HTTPS
                host = API_DOMAIN
                url {
                    encodedPathSegments = arrayListOf(token, "setWebhook")
                }
            }
            contentType(ContentType.Application.Json)
            setBody(parameters)
        }.body<Response<Boolean>>()

        return when (response) {
            is Response.Error -> throw TelegramException.External(response)
            is Response.Result -> {}
            else -> throw TelegramException.Internal("Unknown response type M${response::class.simpleName}>")
        }
    }
}
