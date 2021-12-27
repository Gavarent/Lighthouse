import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kapt)
}

val appProperties = Properties().apply {
    load(File("R:\\YandexDisk\\android\\lighthouse.properties").inputStream())
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.gavarent.lighthouse"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    signingConfigs {
        create("release") {
//            storeFile = File(appProperties.getProperty("release.storeFile"))
//            storePassword = appProperties.getProperty("release.storePassword")
//            keyAlias = appProperties.getProperty("release.keyAlias")
//            keyPassword = appProperties.getProperty("release.keyPassword")
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                isShrinkResources = false
                isDebuggable = false
                signingConfig = signingConfigs.getByName("release")
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
            debug {
                isDebuggable = true
                manifestPlaceholders["androidGeoApiKey"] =
                    appProperties.getProperty("ANDROID_GEO_API_KEY", "")

            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidX)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.test)

    implementation(libs.bundles.koin)

    implementation(libs.bundles.google.maps)
    implementation("com.android.volley:volley:1.2.1")
//    implementation(libs.androidx.appcompat)
//    implementation(libs.iconics.core)


    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
}