plugins {
    `kotlin-dsl`
}
repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(kotlinLibraries.gradle)
    implementation(inkLibraries.publishing)
    implementation(kotlinLibraries.serialization.plugin)
}
