rootProject.name = "telegram-kotlin-sdk"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libraries") {
            from(fileTree("gradle/versions").matching {
                include("*.toml")
            })
        }
    }
}

include("structures")
