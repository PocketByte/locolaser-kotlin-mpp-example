import ru.pocketbyte.locolaser.*

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("ru.pocketbyte.locolaser.kmp")
}

group = "ru.pocketbyte.locolaser.kotlin_multiplatform_example"
version = "1.0"

android {
    namespace = "ru.pocketbyte.locolaser.example.common"
    compileSdk = rootProject.extra["android_sdk_compile"] as Int
    defaultConfig {
        minSdk = rootProject.extra["android_sdk_min"] as Int
    }
}

kotlin {
    androidTarget()
    jvm()
    js(IR) {
        browser()
    }
    iosArm64 {
        binaries { framework("ios_app_framework") }
    }
    iosX64 {
        binaries { framework("ios_app_framework") }
    }
    iosSimulatorArm64 {
        binaries { framework("ios_app_framework") }
    }

    sourceSets {
        val iosMain by creating { dependsOn(commonMain.get()) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
        commonMain.dependencies {
            api("ru.pocketbyte.locolaser:runtime:2.6.0")
        }
    }
}

localize {
    config {
        locales = setOf("base")
        duplicateComments = false
        dependsOnCompileTasks()
        source {
            android {
                resourcesDir = "../app_android/src/main/res/"
            }
        }
        platform {
            kotlinMultiplatform(project) {
                srcDir = "./build/generated/locolaser/"
                repositoryInterface = "StringRepository"
                repositoryPackage = "ru.pocketbyte.locolaser.kmpp"
                android { className = "AndroidStringRepository" }
                ios { className = "IosStringRepository" }
                js { className = "JsStringRepository" }
            }
        }
    }
}
