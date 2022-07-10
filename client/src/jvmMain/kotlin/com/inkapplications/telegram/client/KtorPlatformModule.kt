package com.inkapplications.telegram.client

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

internal actual object KtorPlatformModule {
    actual val engine: HttpClientEngineFactory<*> = OkHttp
}
