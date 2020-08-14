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

    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    implementation(project(Modules.tools))
    implementation(project(Modules.presentation))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.commonApi))

    implementation(project(Modules.featureUserAccountApi))
    implementation(project(Modules.featureNewsFeedApi))

    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)

    implementation(Deps.AndroidX.Lifecycle.Savedstate)
    implementation(Deps.AndroidX.Lifecycle.LifecycleExtensions)
    implementation(Deps.AndroidX.Lifecycle.LifecycleViewmodelKts)

    implementation(Deps.Jetbrains.Kotlinx.Coroutines.AndroidCoroutines)

    implementation(Deps.Google.DaggerGroup.Dagger)
    kapt(Deps.Google.DaggerGroup.DaggerCompiler)

    implementation(Deps.Navigation.Cicerone.Cicerone)
}