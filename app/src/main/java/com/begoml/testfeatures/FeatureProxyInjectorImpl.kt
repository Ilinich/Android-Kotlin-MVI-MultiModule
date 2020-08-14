package com.begoml.testfeatures

import com.begoml.common_api.FeatureProxyInjector
import com.begoml.feature_news_feed_impl.di.component.NewsFeedFlowComponent
import com.begoml.feature_user_account_impl.di.components.UserAccountFlowComponent
import com.begoml.testfeatures.di.AppComponent
import com.begoml.testfeatures.di.dependencies.DaggerFeatureFeatureNewsFeedDependenciesComponent
import com.begoml.testfeatures.di.dependencies.DaggerFeatureUserAccountDependenciesComponent
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

    override fun injectNewsFeedFeature() {
        val appComponent = AppComponent.get()

        val dependenciesUserAccount = DaggerFeatureUserAccountDependenciesComponent.builder()
            .appProvider(appComponent)
            .build()

        UserAccountFlowComponent.init(dependenciesUserAccount)
        val userAccountProvider = UserAccountFlowComponent.get()

        val dependencies = DaggerFeatureFeatureNewsFeedDependenciesComponent.builder()
            .appProvider(appComponent)
            .userAccountProvider(userAccountProvider)
            .build()

        NewsFeedFlowComponent.init(dependencies)

    }

    override fun clearNewsFeedFeature() {

    }
}
