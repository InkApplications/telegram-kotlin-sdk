plugins {
    id("library.multiplatform")
    id("com.inkapplications.publishing")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.structures)
                implementation(ktorLibraries.client.core)
                implementation(ktorLibraries.client.contentnegotiation)
                implementation(ktorLibraries.serialization.json)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(ktorLibraries.client.okhttp)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(ktorLibraries.client.js)
            }
        }
    }
}
