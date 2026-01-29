plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.jupiter.plugin)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "android.architecture.app"
    compileSdk = 36


    buildFeatures {
        compose = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "android.architecture.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "API_URL", "\"http://192.168.1.100:8080/api/\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"http://192.168.1.61:8080/api/prod/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.ok.http)
    implementation(libs.ok.http.logs)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.coil.compose)
    implementation(libs.coil.network)
    implementation("androidx.compose.material:material-icons-extended-android:1.6.7")

    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Instrumented Tests
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.compose.test)
}