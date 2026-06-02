plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.pocketbyte.locolaser.example"
    compileSdk = rootProject.extra["android_sdk_compile"] as Int

    defaultConfig {
        applicationId = "ru.pocketbyte.locolaser.example"
        minSdk = rootProject.extra["android_sdk_min"] as Int
        targetSdk = rootProject.extra["android_sdk_target"] as Int
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packaging {
        resources.excludes += "META-INF/main.kotlin_module"
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation(project(":common"))
}
