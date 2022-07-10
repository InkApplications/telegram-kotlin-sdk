plugins {
    id("library.multiplatform")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.structures)
                implementation(libraries.ktor.client.core)
                implementation(libraries.ktor.client.contentnegotiation)
                implementation(libraries.ktor.serialization.json)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libraries.ktor.client.okhttp)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libraries.ktor.client.js)
            }
        }
    }
}
