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
}
