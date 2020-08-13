package com.begoml.testfeatures

import com.begoml.testfeatures.di.AppComponent
import com.begoml.testfeatures.di.dependencies.DaggerFeatureUserAccountDependenciesComponent
import com.begoml.common_api.FeatureProxyInjector
import com.begoml.feature_user_account_impl.di.components.UserAccountFlowComponent
import javax.inject.Inject

class FeatureProxyInjectorImpl @Inject constructor() : FeatureProxyInjector {

    override fun injectUserAccountFeature() {
        val appComponent = AppComponent.get()

        val dependencies = DaggerFeatureUserAccountDependenciesComponent.builder()
            .appProvider(appComponent)
            .build()

        UserAccountFlowComponent.init(dependencies)
    }

    override fun clearUserAccountFeature() {
        UserAccountFlowComponent.clear()
    }
}
