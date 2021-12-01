rootProject.name = "Lighthouse"
include(":app")

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version ("7.1.0-alpha13")
        id("com.android.library") version ("7.1.0-alpha13")
        id("org.jetbrains.kotlin.android") version ("1.5.21")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("compose", "1.0.1")
            version("accompanistVersion", "0.21.3-beta")
            version("koinVersion", "3.1.4")

            alias("androidx-core")
                .to("androidx.core", "core-ktx").version("1.6.0")
            alias("compose-ui")
                .to("androidx.compose.ui", "ui").versionRef("compose")
            alias("compose-material")
                .to("androidx.compose.material", "material").versionRef("compose")
            alias("compose-ui-tooling-preview")
                .to("androidx.compose.ui", "ui-tooling-preview").versionRef("compose")

            alias("androidx-lifecycle-runtime")
                .to("androidx.lifecycle", "lifecycle-runtime-ktx").version("2.4.0")
            alias("activity-compose")
                .to("androidx.activity", "activity-compose").version("1.4.0")


            alias("junit")
                .to("junit", "junit").version("4.13.2")

            alias("androidx.test.ext")
                .to("androidx.test.ext", "junit").version("1.1.3")
            alias("androidx.test.espresso")
                .to("androidx.test.espresso", "espresso-core").version("3.4.0")
            alias("androidx.compose.ui-test-junit4")
                .to("androidx.compose.ui", "ui-test-junit4").versionRef("compose")

            alias("androidx.compose.ui-tooling")
                .to("androidx.compose.ui", "ui-tooling").versionRef("compose")

            alias("accompanist-systemuicontroller")
                .to("com.google.accompanist", "accompanist-systemuicontroller")
                .versionRef("accompanistVersion")
            alias("accompanist-navigation-material")
                .to("com.google.accompanist", "accompanist-navigation-material")
                .versionRef("accompanistVersion")
            alias("accompanist-navigation-animation")
                .to("com.google.accompanist", "accompanist-navigation-animation")
                .versionRef("accompanistVersion")

            alias("koin-core")
                .to("io.insert-koin", "koin-core").versionRef("koinVersion")
            alias("koin-android")
                .to("io.insert-koin", "koin-android").versionRef("koinVersion")
            alias("koin-androidx-compose")
                .to("io.insert-koin", "koin-androidx-compose").versionRef("koinVersion")

            alias("google.maps.libraries")
                .to("com.google.android.libraries.maps", "maps").version("3.1.0-beta")
            alias("google.maps.android")
                .to("com.google.maps.android", "maps-v3-ktx").version("3.2.0")

//            alias("google.maps.libraries")
//                .to("com.google.android.libraries.maps", "maps").version("3.1.0-beta")


//            alias("google.maps.android.v3")
//                .to("com.google.maps.android", "maps-v3-ktx").version("3.2.0")
//            alias("google.maps.android.maps")
//                .to("com.google.maps.android", "maps-utils-v3-ktx").version("3.2.0")

//            alias("google.android.libraries")
//                .to("com.google.android.libraries.maps", "maps").version("3.2.0")
//            alias("google.maps.android")
//                .to("com.google.maps.android", "maps-utils-v3-ktx").version("3.2.0")


//            const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha04"


            bundle("androidX", listOf("androidx-core"))
            bundle(
                "compose",
                listOf("compose-ui", "compose-material", "compose-ui-tooling-preview")
            )
            bundle(
                "koin",
                listOf("koin-core", "koin-android", "koin-androidx-compose")
            )
        }
    }
}
