
### Best-practices infrastructure for starting a new Android app, *fast.* 🔥
Start your next app ***less than five minutes*** with full support for the Gradle Kotlin DSL, Dagger 2, multi-module builds, and CI. 

Minimally opinionated, it provides:

- **100% Kotlin**
- **Generated Android project** as created by Android Studio (3.5 Canary 10)
- **Gradle [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)**
- **buildSrc [pattern](https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/)** for better dependency management and multi-module development
- **Dagger 2 pre-configured -** with *just* enough code to help understand the tricky [Kotlin](https://github.com/petedoyle/android-starter/blob/fa07f0b2eb14193732ebec610974c082af81f524/app/src/main/java/dev/petedoyle/starter/features/main/MainModule.kt#L11-L22) [syntax](https://github.com/petedoyle/android-starter/blob/fa07f0b2eb14193732ebec610974c082af81f524/app/src/main/java/dev/petedoyle/starter/features/main/MainActivity.kt#L12-L13)
- **Kotlin experimental extensions** so you can use [Parcelize](https://kotlinlang.org/docs/tutorials/android-plugin.html#parcelable) on data classes
- **Packaging by feature -** Lots of benefits, but also makes it easier to later use Gradle modules to improve build speeds
- **Kotlin code formatting -** based on the official [Kotlin coding conventions](https://kotlinlang.org/docs/reference/coding-conventions.html), with small tweaks:
     - 120 character line limit
     - 4 space continuation indent (instead of 8)
     - Support for `@formatter:on` and `@formatter:off` tags

It *does not provide*:

- **Architecture -** Use MVVM, MVP, MVI, or roll your own.
- **Persistence -** Use Room, SQLDelight, etc.
- **Other Libraries -** 
    - Async - Use RxJava, Coroutines, etc.
    - HTTP loading - Use Retrofit, etc.
    - JSON parsing - Use GSON, Moshi, etc.
    - Photo loading - Use Picasso, Glide, etc.
    - Logging - Use Timber, etc.
    - Crash logging - Use Crashlytics, etc.


#### Coming soon
- **Rename default package** (currently `dev.petedoyle.starter`)
- **Incremental annotation processing -** for faster builds (waiting for a few more annotation processors to [support](https://github.com/gradle/gradle/blob/master/subprojects/docs/src/docs/userguide/java_plugin.adoc#state-of-support-in-popular-annotation-processors) it)
- **Automatic `versionCode` management -** for CI builds
- Sample CircleCI config that:
    - Runs espresso tests on an emulator
    - Manual approval step to automatically the development branch to `master`
    - Manual approval step to push the newest build from `master` to Google Play
    - (as samples to start from)

#### Investigating
- Fastlane support

## Starting a new app
1. Decide on your app's repo name (e.g. `myapp`)

    ```
    $ KINDLING_REPO_NAME=myapp
    ```
2. Create your new repo:

    ```
    $ git clone https://github.com/petedoyle/kindling.git $KINDLING_REPO_NAME && \
        cd $KINDLING_REPO_NAME && \
        AS_VERSION="$(git rev-parse --abbrev-ref HEAD)@$(git rev-parse --short HEAD)" && \
        rm -rf .git && \
        git init && \
        git add . && \
        git commit -m "Create new project from github.com/petedoyle/kindling ($AS_VERSION)" && \
        unset KINDLING_REPO_NAME
    ```

3. Open `myapp/build.gradle.kts` in Android Studio. Or, if you've installed the command line launcher:

    ```
    $ studio build.gradle.kts
    ```

***That's it! You're ready to dev!*** 🔥

## Finding your way around

- See `MainActivity` to see the sample activity. Note the package: `dev.petedoyle.starter.feature.main`.
- See `MainModule` to see how `MainActivity`'s dependencies can be provided. The lifetime of `MainModule`'s object graph is scoped to `MainActivity` (because `@ContributesAndroidInjector` in `ActivityBindingModule` created a Dagger `Subcomponent` under the hood, scoped to `MainActivity`).
- See `AppModule` to see how app-wide dependencies can be provided.
- See `buildSrc/src/main/java/Dependencies.kt` for declaring libraries and their versions.
- See `app/build.gradle` to see how to consume libraries, e.g.

    ```
    implementation(Deps.androidx_appcompat)
    ```
- See `daggerutil/build.gradle.kts` to see how to set up a module's build script.
- See how multiple modules' `build.gradle.kts` refer to `Deps.[name]`, thus allowing you to update dependency versions across many gradle modules at once.

# License

    Copyright 2019 Pete Doyle

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
