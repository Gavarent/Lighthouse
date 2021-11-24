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

            bundle("androidX", listOf("androidx-core"))
            bundle(
                "compose",
                listOf("compose-ui", "compose-material", "compose-ui-tooling-preview")
            )
        }
    }
}
