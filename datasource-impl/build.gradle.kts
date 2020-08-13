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

    implementation(project(Modules.dataSourceApi))

    implementation(Deps.Javax.Inject.Inject)

    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)

    implementation(Deps.Jetbrains.Kotlinx.Coroutines.AndroidCoroutines)

    implementation(Deps.Google.Room.RoomRuntime)
    kapt(Deps.Google.Room.RoomCompiler)

    implementation(Deps.Google.Room.RoomKtx)
}