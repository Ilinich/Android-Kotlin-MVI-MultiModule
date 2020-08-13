object Deps {

    object Jetbrains {
        object Kotlin : Group(name = "org.jetbrains.kotlin") {

            object Plugin {
                object Gradle :
                    Dependency(group = Kotlin, name = "kotlin-gradle-plugin", version = Versions.kotlinVersion)

                object Serialization :
                    Dependency(group = Kotlin, name = "kotlin-serialization", version = Versions.kotlinVersion)
            }

            object StdLib {
                object Common :
                    Dependency(group = Kotlin, name = "kotlin-stdlib-common", version = Versions.kotlinVersion)

                object Jdk7 :
                    Dependency(group = Kotlin, name = "kotlin-stdlib-jdk7", version = Versions.kotlinVersion)
            }

            object Test {
                object Common :
                    Dependency(group = Kotlin, name = "kotlin-test-common", version = Versions.kotlinVersion)

                object Junit :
                    Dependency(group = Kotlin, name = "kotlin-test-junit", version = Versions.kotlinVersion)
            }

            object TestAnnotations {
                object Common : Dependency(
                    group = Kotlin,
                    name = "kotlin-test-annotations-common",
                    version = Versions.kotlinVersion
                )
            }
        }

        object Kotlinx : Group(name = "org.jetbrains.kotlinx") {
            object Serialization {
                private const val version = "0.20.0"

                object RuntimeCommon : Dependency(group = Kotlinx, name = "kotlinx-serialization-runtime-common", version = version)
                object Runtime : Dependency(group = Kotlinx, name = "kotlinx-serialization-runtime", version = version)
                object RuntimeNative : Dependency(group = Kotlinx, name = "kotlinx-serialization-runtime-native", version = version)
            }

            object Coroutines {

                object AndroidCoroutines : Dependency(group = Kotlinx, name = "kotlinx-coroutines-android", version = Versions.coroutinesAndroidVersion)
            }

        }
    }

    object Android {
        object Tools {
            object Build : Group(name = "com.android.tools.build") {
                object Gradle : Dependency(group = Build, name = "gradle", version = "3.5.2")
            }
        }
    }

    object AndroidX {
        object Core : Group(name = "androidx.core") {
            object Ktx : Dependency(group = Core, name = "core-ktx", version = "1.1.0")
        }

        object AppCompat : Group(name = "androidx.appcompat") {
            object AppCompat :
                Dependency(group = AndroidX.AppCompat, name = "appcompat", version = "1.1.0")
        }

        object RecyclerView : Group(name = "androidx.recyclerview") {
            object RecyclerView :
                Dependency(group = AndroidX.RecyclerView, name = "recyclerview", version = "1.1.0")
        }

        object ConstraintLayout : Group(name = "androidx.constraintlayout") {
            object ConstraintLayout : Dependency(
                group = AndroidX.ConstraintLayout,
                name = "constraintlayout",
                version = "1.1.3"
            )
        }
    }

    object Google {
        object Android {
            object Material : Group(name = "com.google.android.material") {
                object Material :
                    Dependency(group = Android.Material, name = "material", version = "1.1.0")
            }
        }

        object ArchComponents {

            object Lifecycle : Group(name = "android.arch.lifecycle") {

                object Extensions :
                    Dependency(group = Lifecycle, name = "extensions", version = Versions.lifecycleVersion)

                object Compiler :
                    Dependency(group = Lifecycle, name = "compiler", version = Versions.lifecycleVersion)
            }
        }

        object DaggerGroup : Group(name = "com.google.dagger") {

            object Dagger : Dependency(group = DaggerGroup, name = "dagger", version = Versions.daggerVersion)
            object DaggerCompiler : Dependency(group = DaggerGroup, name = "dagger-compiler", version = Versions.daggerVersion)

        }

        object Room : Group(name = "androidx.room") {

            object RoomRuntime : Dependency(group = Room, name = "room-runtime", version = Versions.roomVersion)
            object RoomCompiler : Dependency(group = Room, name = "room-compiler", version = Versions.roomVersion)
            object RoomKtx : Dependency(group = Room, name = "room-ktx", version = Versions.roomVersion)
        }
    }

    object Navigation {

        object Cicerone : Group(name = "ru.terrakok.cicerone") {

            object Cicerone : Dependency(group = Navigation.Cicerone, name = "cicerone", version = "5.0.0")
        }
    }

    object Javax {

        object Inject : Group(name = "javax.inject") {

            object Inject : Dependency(group = Javax.Inject, name = "javax.inject", version = "1")
        }
    }

    open class Group(val name: String)

    open class Dependency private constructor(
        private val notation: String
    ) : CharSequence by notation {
        constructor(
            group: Group,
            name: String,
            version: String
        ) : this("${group.name}:$name:$version")

        override fun toString(): String = notation
    }
}