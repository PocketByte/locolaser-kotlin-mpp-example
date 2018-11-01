# LocoLaser Kotlin Multiplatform Example
[LocoLaser](https://github.com/PocketByte/LocoLaser/) - Localozation tool that generate localization files for various platforms depends on common source.
<br>This example shows how to use LocoLaser in Kotlin Mobile Multiplatform projects to generate localized strings repository class with common interface for both mobile platforms: Android and iOS.

##### 1 Step: Apply gradle plugin
Example uses standard way to apply custom gradle plugin.<br>
In **`build.gradle`** of the project was added maven repository and classpath:
```gradle
buildscript {
  repositories {
    maven {
      // 1.1: Add maven repository
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    // 1.2: Add classpath dependency
    classpath "gradle.plugin.ru.pocketbyte.locolaser:plugin:1.0.2"
  }
}
```
Apply plugin in the same **`build.gradle`**:
```
// 1.3: Apply LocoLaser plugin
apply plugin: "ru.pocketbyte.locolaser"
```

##### 2 Step: Add 'localize' dependensy
Choose which type of artifact you will use and add them as **`localize`** dependency. This example uses artifact to work with Kotlin Mobile Multiplatform projects:
```gradle
dependencies {
    // 2: Add dependency for Kotlin mobile platforms
    localize "ru.pocketbyte.locolaser:platform-kotlin-mobile:1.2.4"
}
```

##### 3 Step: Create configuration file
How to build config file you can find in Wiki: [Platform: Kotlin Mobile Multiplatform](https://github.com/PocketByte/LocoLaser/wiki/Platform:-Kotlin-Mobile-Multiplatform) By default this config file should have name`"localize_config.json"` and be placed in the root folder of the project.

##### 4 Step: Run task 'localize' before build
This example run **`:localize`** task before each build. To do it the following modules has following task dependencies in **`build.gradle`**:<br>
**`common/build.gradle`**:
```gradle
// 4.1 Run task ‘localize’ before each build
compileKotlinCommon.dependsOn {
   project.tasks.getByPath(':localize')
}
```
**`app_android/build.gradle`**:
```gradle
// 4.2 Run task ‘localize’ before each build
preBuild.dependsOn {
   project.tasks.getByPath(':localize')
}
```
**`app_ios/build.gradle`**:
```gradle
// 4.3 Run task ‘localize’ before each build
checkKonanCompiler.dependsOn {
   project.tasks.getByPath(':localize')
}
```


##### 5 Step: Init repository
As a final step instance of Strings repository should be created. This example use common object [Repository](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/common/src/main/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt) with ‘expect’ modifier. This object should be implemented for each platform independently.
In Android application it's better to implement [standard singleton](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/impl_jvm/src/main/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt) and initialize it in `Application` class in method `onCreate`:
```kotlin
// 5.1 Init Android Repository
Repository.initInstance(AndroidStringRepository(this))
```
In iOS application it's better to initialize property right in [property declaration](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/app_ios/src/main/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt)

##### Usage in code
Now any string can be received using code as following:
```kotlin
Repository.str.screen_main_hello_text
```
## License
```
Copyright © 2017 Denis Shurygin. All rights reserved.
Contacts: <mail@pocketbyte.ru>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```