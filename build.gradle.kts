// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    Deps.applyBuildRepos(repositories)

    dependencies {
        classpath(Deps.Build.gradle_android)
        classpath(Deps.Build.kotlin_gradle_plugin)
    }
}

allprojects {
    Deps.applyProjectRepos(repositories)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
