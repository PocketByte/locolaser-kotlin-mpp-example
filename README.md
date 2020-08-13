# LocoLaser Kotlin Multiplatform Example
[LocoLaser](https://github.com/PocketByte/LocoLaser/) - Localization tool that generate localization files for various platforms depends on common source.
<br>This example shows how to use LocoLaser in Kotlin Mobile Multiplatform projects to generate localized strings repository class with common interface for both mobile platforms: Android and iOS.

##### 1 Step: Apply gradle plugin
Example uses standard way to apply custom gradle plugin.<br>
In **`build.gradle`** of the project was added maven repository and classpath:
```groovy
buildscript {
  repositories {
    maven {
      // 1.1: Add maven repository
      url "https://plugins.gradle.org/m2/"
    }
    
    ...
  }
  dependencies {
    // 1.2: Add classpath dependency
    classpath "gradle.plugin.ru.pocketbyte.locolaser:plugin:1.0.4"
    
    ...
  }
}
```
Apply plugin in **`build.gradle`** of common module:
```groovy
// 1.3: Apply LocoLaser plugin
apply plugin: "ru.pocketbyte.locolaser"
```

##### 2 Step: Add 'localize' dependency
Choose which type of artifact you will use and add them as **`localize`** dependency. This example uses artifact to work with Kotlin Mobile Multiplatform projects:
```groovy
dependencies {
    // 2: Add dependency for Kotlin Multiplatform
    localize "ru.pocketbyte.locolaser:platform-kotlin-mpp:1.5.0"
    
    ...
}
```

##### 3 Step: Create configuration file
How to build config file you can find in Wiki: [Platform: Kotlin Mobile Multiplatform](https://github.com/PocketByte/LocoLaser/wiki/Platform:-Kotlin-Mobile-Multiplatform) By default this config file should have name`"localize_config.json"` and be placed in the root folder of the project.

##### 4 Step: Run task 'localize' before build
This example run **`:localize`** task before each build. To do it add task dependencies in **`build.gradle`** of common module:
```groovy
// 4 Run task ‘localize’ before each build
preBuild.dependsOn {
    project.tasks.getByPath(':common:localize')
}
```


##### 5 Step: Init repository
As a final step instance of Strings repository should be created. This example use common object [Repository](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/common/src/commonMain/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt) with ‘expect’ modifier. This object should be implemented for each platform independently.
In Android application it's better to implement [standard singleton](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/common/src/androidMain/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt) and initialize it in `Application` class in method `onCreate`:
```kotlin
// 5.1 Init Android Repository
Repository.initInstance(AndroidStringRepository(this))
```
In iOS application it's better to initialize property right in [property declaration](https://github.com/PocketByte/locolaser-kotlin-mpp-example/blob/master/common/src/iosMain/kotlin/ru/pocketbyte/locolaser/example/repository/Repository.kt)

JS implementation require `i18next` object as parameter So at first first add dependency with externals into common **`build.gradle`**:
```groovy
kotlin {
    sourceSets {
        jsMain {
            // 5.2 Add i18next dependency
            dependencies {
                api "ru.pocketbyte.locolaser:i18next-externals:1.0"
            }
        }
    }
}
```
Then you should initialize it before use:
```kotlin
i18next.init(localizationConfig) { _, _ ->
    // 5.3 Init JS Repository
    Repository.init(JsStringRepository(i18next))
    ...
}
```

##### Usage in code
Now any string can be received using code as following:
```kotlin
Repository.str.screen_main_hello_text
```

##### 6 Step: Move generated files into build folder
Because String Repository classes generated depends on string resources, no need to hold them together with other source classes and commit into git. This example overrides `res_dir` of kotlin classes and put them into folders `./build/generated/locolaser/[platform]`. To tell gradle plugin about this files, add source dirs into **`gradle.build`** of common module:
```groovy

kotlin {
    sourceSets {
        commonMain {
            // 6.1 Add source dir for generated common classes
            kotlin.srcDir('./build/generated/locolaser/common/')
            
            ...
        }

        androidMain {
            // 6.2 Add source dir for generated android classes
            kotlin.srcDir('./build/generated/locolaser/android/')
            
            ...
        }

        jsMain {
            // 6.3 Add source dir for generated js classes
            kotlin.srcDir('./build/generated/locolaser/js/')

            ...
        }

        iosMain {
            // 6.4 Add source dir for generated iOS classes
            kotlin.srcDir('./build/generated/locolaser/ios/')
            
            ...
        }
    }
    ...
}
```

## License
```
Copyright © 2019 Denis Shurygin. All rights reserved.
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
