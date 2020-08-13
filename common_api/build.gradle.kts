plugins {
    id(Plugins.androidLibrary)
    kotlin("android")
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
    
    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)

    api(Deps.Javax.Inject.Inject)

    api(Deps.Navigation.Cicerone.Cicerone)

    implementation(project(Modules.dataSourceApi))

}