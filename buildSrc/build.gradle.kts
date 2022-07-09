plugins {
    `kotlin-dsl`
}
repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(libraries.kotlin.gradle)
    implementation(libraries.ink.publishing)
    implementation(libraries.kotlinx.serialization.plugin)
}
