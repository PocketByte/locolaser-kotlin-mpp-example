plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

group = "ru.pocketbyte.locolaser"
version = "1.0"

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
            dependencies {
                implementation(project(":common"))
            }
        }
    }
}
