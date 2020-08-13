plugins {
    id(Plugins.application)

    kotlin(Plugins.android)
    kotlin(Plugins.androidExtensions)
    kotlin(Plugins.kapt)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = Config.applicatiınId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(project(Modules.tools))
    implementation(project(Modules.presentation))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.commonApi))

    implementation(project(Modules.dataSourceApi))
    implementation(project(Modules.dataSourceImpl))

    // feature
    implementation(project(Modules.featureUserAccountImpl))
    implementation(project(Modules.featureUserAccountApi))

    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)

    implementation(Deps.Google.DaggerGroup.Dagger)
    kapt(Deps.Google.DaggerGroup.DaggerCompiler)

    implementation(Deps.Google.Android.Material.Material)

    implementation(Deps.Navigation.Cicerone.Cicerone)

    implementation(Deps.Google.Room.RoomRuntime)
    kapt(Deps.Google.Room.RoomCompiler)
    implementation(Deps.Google.Room.RoomKtx)

}

