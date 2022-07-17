rootProject.name = "telegram-kotlin-sdk"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    versionCatalogs {
        create("inkLibraries") {
            from(files("gradle/versions/ink.toml"))
        }
        create("kotlinLibraries") {
            from(files("gradle/versions/kotlin.toml"))
        }
        create("ktorLibraries") {
            from(files("gradle/versions/ktor.toml"))
        }
    }
}

include("cli")
include("client")
include("structures")
