import org.gradle.api.artifacts.dsl.RepositoryHandler

private object Versions {
    const val androidx_appcompat = "1.0.2"
    const val androidx_constraintlayout = "1.1.3"
    const val androidx_ktx = "1.0.1"
    const val dagger = "2.22.1"
    const val javax_annotation_jsr250 = "1.0"
    const val kotlin = "1.3.21"

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
    const val minSdkVersion = 15
    const val targetSdkVersion = 27
    const val compileSdkVersion = 28

    // Test Runner
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    // Dependencies
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    const val androidx_ktx = "androidx.core:core-ktx:${Versions.androidx_ktx}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val javax_annotation_jsr250 = "javax.annotation:jsr250-api:${Versions.javax_annotation_jsr250}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

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