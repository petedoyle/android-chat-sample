plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(Deps.compileSdkVersion)

    defaultConfig {
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

    implementation(Deps.kotlin_stdlib)

    // Dagger
    kapt(Deps.dagger_compiler)
    kapt(Deps.dagger_android_processor)
    implementation(Deps.dagger_android)
    implementation(Deps.dagger_android_support)
    compileOnly(Deps.javax_annotation_jsr250)

    // Testing
    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.AndroidTest.androidx_junit)
    androidTestImplementation(Deps.AndroidTest.espresso)
}
