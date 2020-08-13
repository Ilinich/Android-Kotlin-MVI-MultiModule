plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
    }
}

dependencies {

    implementation(project(Modules.tools))

    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)

    implementation(Deps.Jetbrains.Kotlinx.Coroutines.AndroidCoroutines)
}