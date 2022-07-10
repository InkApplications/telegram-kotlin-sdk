package com.inkapplications.telegram.client

import io.ktor.client.engine.*

/**
 * Internal platform specific dependencies.
 */
internal expect object KtorPlatformModule {
    val engine: HttpClientEngineFactory<*>
}
