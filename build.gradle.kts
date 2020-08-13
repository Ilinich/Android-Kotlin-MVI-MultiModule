buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(ClassPaths.gradleClasspath)
        classpath(ClassPaths.kotlinGradlePluginClasspath)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}