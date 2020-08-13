package com.begoml.testfeatures.di

import android.content.Context
import com.begoml.testfeatures.AppActivity
import com.begoml.testfeatures.CharityAppImpl
import com.begoml.testfeatures.di.modules.*
import com.begoml.common_api.di.AppProvider
import com.begoml.presentation.exception.InitComponentException
import com.begoml.testfeatures.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        SharedPreferencesModule::class,
        DatabaseModule::class,
        NavigationModule::class,
        DataSourceModule::class
    ]
)
@Singleton
interface AppComponent : AppProvider {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    fun inject(where: CharityAppImpl)
    fun inject(where: AppActivity)

    companion object {

        private var component: AppComponent? = null

        fun init(context: Context) {
            component ?: DaggerAppComponent.builder()
                .context(context)
                .build().apply {
                    component = this
                }
        }

        fun get(): AppComponent {
            return component ?: throw InitComponentException()
        }
    }
}