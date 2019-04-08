import org.gradle.api.artifacts.dsl.RepositoryHandler

private object Versions {
    const val androidx_appcompat = "1.0.2"
    const val androidx_cardview = "1.0.0"
    const val androidx_constraintlayout = "1.1.3"
    const val androidx_core_testing = "2.0.1"
    const val androidx_ktx = "1.0.1"
    const val androidx_lifecycle = "2.1.0-alpha04"
    const val androidx_paging = "2.1.0"
    const val androidx_room = "2.1.0-alpha06"
    const val dagger = "2.22.1"
    const val javax_annotation_jsr250 = "1.0"
    const val kotlin = "1.3.21"
    const val moshi = "1.8.0"
    const val recyclerview = "1.0.0"
    const val rxandroid = "2.1.1"
    const val rxjava = "2.2.8"
    const val timber = "4.7.1"

    object Build {
        const val android_gradle_plugin = "3.5.0-alpha10"
        const val kotlin_gradle_plugin = Versions.kotlin
    }

    object Test {
        const val junit = "4.12"
    }

    object AndroidTest {
        const val androidx_junit = "1.1.0" // androidx.test.ext:junit
        const val espresso = "3.1.1"
    }
}

@Suppress("unused")
object Deps {
    // @formatter:off
    // SDK Versions
    const val minSdkVersion = 21
    const val targetSdkVersion = 27
    const val compileSdkVersion = 28

    // Test Runner
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    /*
        // ViewModel and LiveData
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"

    // alternatively - just ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version" // For Kotlin use lifecycle-viewmodel-ktx

    // alternatively - just LiveData
    implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"

    // alternatively - Lifecycles only (no ViewModel or LiveData). Some UI
    //     AndroidX libraries use this lightweight import for Lifecycle
    implementation "androidx.lifecycle:lifecycle-runtime:$lifecycle_version"

    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version" // For Kotlin use kapt instead of annotationProcessor
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"

    // optional - ReactiveStreams support for LiveData
    implementation "androidx.lifecycle:lifecycle-reactivestreams:$lifecycle_version" // For Kotlin use lifecycle-reactivestreams-ktx

    // optional - Test helpers for LiveData
    testImplementation "androidx.arch.core:core-testing:$lifecycle_version"
     */

    // Dependencies

    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_cardview = "androidx.cardview:cardview:${Versions.androidx_cardview}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    const val androidx_core_testing = "androidx.arch.core:core-testing:${Versions.androidx_core_testing}"
    const val androidx_lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.androidx_lifecycle}"
    const val androidx_lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle}"
    const val androidx_lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle}"
    const val androidx_lifecycle_reactivestreams_ktx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.androidx_lifecycle}"
    const val androidx_ktx = "androidx.core:core-ktx:${Versions.androidx_ktx}"
    const val androidx_paging_ktx = "androidx.paging:paging-runtime-ktx:${Versions.androidx_paging}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val javax_annotation_jsr250 = "javax.annotation:jsr250-api:${Versions.javax_annotation_jsr250}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.androidx_room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.androidx_room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.androidx_room}"
    const val room_rxjava = "androidx.room:room-rxjava2:${Versions.androidx_room}"
    const val room_testing = "androidx.room:room-testing:${Versions.androidx_room}"

    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Build {
        const val gradle_android = "com.android.tools.build:gradle:${Versions.Build.android_gradle_plugin}"
        const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Build.kotlin_gradle_plugin}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
    }

    object AndroidTest {
        const val androidx_junit = "androidx.test.ext:junit:${Versions.AndroidTest.androidx_junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espresso}"
    }
    // @formatter:on

    /**
     * Apply common repos for the `buildscript` block.
     *
     * To use, add the following to a module's `build.gradle`:
     *
     * ```
     * buildscript {
     *     Deps.applyBuildRepos(repositories)
     * }
     * ```
     */
    fun applyBuildRepos(handler: RepositoryHandler) {
        handler.google()
        handler.mavenCentral()
        handler.jcenter() // last to resolve, for security: https://twitter.com/jakewharton/status/1073102730443526144
    }

    /**
     * Apply common repos for a project's `dependencies` block.
     *
     * To use, add the following to a module's `build.gradle`:
     *
     * ```
     * Deps.applyProjectRepos(repositories)
     * ```
     */
    fun applyProjectRepos(handler: RepositoryHandler) {
        handler.google()
        handler.mavenCentral()
        handler.jcenter() // last to resolve, for security: https://twitter.com/jakewharton/status/1073102730443526144
    }
}