import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true // required for @Parcelize on data classes
    })
}

android {
    compileSdkVersion(Deps.compileSdkVersion)

    defaultConfig {
        applicationId = "dev.petedoyle.starter"
        minSdkVersion(Deps.minSdkVersion)
        targetSdkVersion(Deps.targetSdkVersion)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Deps.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":daggerutil"))

    // Common
    implementation(Deps.kotlin_stdlib)

    // Dagger
    kapt(Deps.dagger_compiler)
    kapt(Deps.dagger_android_processor)
    implementation(Deps.dagger_android)
    implementation(Deps.dagger_android_support)
    compileOnly(Deps.javax_annotation_jsr250)

    // Normal
    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_constraintlayout)
    implementation(Deps.androidx_ktx)

    // Testing
    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.AndroidTest.androidx_junit)
    androidTestImplementation(Deps.AndroidTest.espresso)
}
