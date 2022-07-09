plugins {
    id("library.multiplatform")
    kotlin("plugin.serialization")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libraries.kotlinx.serialization.json)
                implementation(libraries.kotlinx.datetime)
            }
        }
    }
}