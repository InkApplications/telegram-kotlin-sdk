plugins {
    application
    kotlin("jvm")
}

application {
    applicationName = "telegram-cli"
    mainClassName = "com.inkapplications.telegram.cli.MainKt"
}

dependencies {
    implementation(projects.client)
    implementation(libraries.kotlinx.coroutines.core)
    implementation(libraries.kotlinx.serialization.json)
    implementation("com.github.ajalt.clikt:clikt:3.4.0")
    implementation(libraries.ktor.serialization.json)
    implementation("io.ktor:ktor-server-content-negotiation:2.0.3")
    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")
}
