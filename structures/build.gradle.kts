plugins {
    id("library.multiplatform")
    kotlin("plugin.serialization")
    id("com.inkapplications.publishing")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlinLibraries.serialization.json)
                api(kotlinLibraries.datetime)
            }
        }
    }
}
