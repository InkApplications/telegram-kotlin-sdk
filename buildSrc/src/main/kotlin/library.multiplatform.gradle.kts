plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

kotlin {
    jvm()
    js {
        nodejs()
        browser()
    }
}

project.extensions.configure(PublishingExtension::class.java) {
    publications {
        withType<MavenPublication> {
            pom {
                name.set("Ink Telegram SDK - ${project.name}")
                description.set("Multiplatform Kotlin SDK for Telegram (unofficial)")
                url.set("https://github.com/inkapplications/telegram-kotlin-sdk")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://choosealicense.com/licenses/mit/")
                    }
                }
                developers {
                    developer {
                        id.set("reneevandervelde")
                        name.set("Renee Vandervelde")
                        email.set("Renee@InkApplications.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/InkApplications/telegram-kotlin-sdk.git")
                    developerConnection.set("scm:git:ssh://git@github.com:InkApplications/telegram-kotlin-sdk.git")
                    url.set("https://github.com/InkApplications/telegram-kotlin-sdk")
                }
            }
        }
    }
}
