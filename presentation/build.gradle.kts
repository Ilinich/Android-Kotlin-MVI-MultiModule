plugins {
    id(Plugins.androidLibrary)

    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
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
    implementation(project(Modules.commonApi))

    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)

    implementation(Deps.Google.ArchComponents.Lifecycle.Extensions)

    implementation(Deps.Navigation.Cicerone.Cicerone)

    implementation(Deps.Javax.Inject.Inject)

    implementation(Deps.Google.DaggerGroup.Dagger)
    kapt(Deps.Google.DaggerGroup.DaggerCompiler)

    implementation(Deps.Navigation.Cicerone.Cicerone)
}