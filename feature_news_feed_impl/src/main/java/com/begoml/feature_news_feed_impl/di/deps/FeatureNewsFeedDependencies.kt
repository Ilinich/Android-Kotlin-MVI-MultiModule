package com.begoml.feature_news_feed_impl.di.deps

import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.feature_user_account_api.UserAccountRepository

interface FeatureNewsFeedDependencies {

    fun providesAppFlowCoordinator(): AppFlowCoordinator

    fun providesFeatureProxyInjector(): FeatureProxyInjector

    fun providesUserAccountRepository(): UserAccountRepository
}