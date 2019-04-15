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
        applicationId = "dev.petedoyle.chatsample"
        minSdkVersion(Deps.minSdkVersion)
        targetSdkVersion(Deps.targetSdkVersion)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Deps.testInstrumentationRunner

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(Pair("room.schemaLocation", "$projectDir/schemas"))
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
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

    // ViewModels, Lifecycle, etc
    implementation(Deps.androidx_lifecycle_livedata)
    implementation(Deps.androidx_lifecycle_viewmodel_ktx)
    kapt(Deps.androidx_lifecycle_compiler)
    testImplementation(Deps.androidx_core_testing)

    // Room
    implementation(Deps.room_runtime)
    kapt(Deps.room_compiler)
    implementation(Deps.room_ktx)
    implementation(Deps.room_rxjava)
    testImplementation(Deps.room_testing)

    // Normal
    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_cardview)
    implementation(Deps.androidx_constraintlayout)
    implementation(Deps.androidx_ktx)
    implementation(Deps.androidx_paging_ktx)
    implementation(Deps.glide)
    kapt(Deps.glide_compiler)
    implementation(Deps.moshi)
    implementation(Deps.recyclerview)
    implementation(Deps.rxandroid)
    implementation(Deps.rxjava)
    implementation(Deps.timber)

    // Testing
    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.AndroidTest.androidx_junit)
    androidTestImplementation(Deps.AndroidTest.espresso)
}
