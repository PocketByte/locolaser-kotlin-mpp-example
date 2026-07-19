plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.0.21" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.android.application") version "8.6.0" apply false
    id("com.android.library") version "8.6.0" apply false
    id("ru.pocketbyte.locolaser.kmp") version "2.7.0" apply false
}

extra["kotlin_version"] = "2.0.21"
extra["android_sdk_compile"] = 34
extra["android_sdk_target"] = 34
extra["android_sdk_min"] = 21

tasks.withType<Delete>().configureEach {
    if (name == "clean") delete(rootProject.layout.buildDirectory)
}
