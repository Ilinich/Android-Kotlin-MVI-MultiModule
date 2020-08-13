package com.begoml.feature_user_account_impl.di.deps

import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator

interface FeatureUserAccountDependencies {

    fun providesAppFlowCoordinator(): AppFlowCoordinator

    fun providesFeatureProxyInjector(): FeatureProxyInjector
}