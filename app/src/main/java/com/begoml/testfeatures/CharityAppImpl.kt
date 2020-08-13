package com.begoml.testfeatures

import android.app.Application
import com.begoml.testfeatures.di.AppComponent
import com.begoml.common_api.CharityApp
import com.begoml.common_api.FeatureProxyInjector
import javax.inject.Inject

class CharityAppImpl : Application(), CharityApp {

    @Inject
    lateinit var featureInitializer: FeatureProxyInjector

    override fun onCreate() {
        super.onCreate()

        AppComponent.init(applicationContext)
        AppComponent.get().inject(this)

    }

    override val featureProxyInjector: FeatureProxyInjector
        get() = featureInitializer
}