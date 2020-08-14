package com.begoml.testfeatures.di.dependencies

import com.begoml.common_api.di.AppProvider
import com.begoml.feature_news_feed_impl.di.deps.FeatureNewsFeedDependencies
import com.begoml.feature_user_account_api.di.UserAccountProvider
import com.begoml.feature_user_account_impl.di.deps.FeatureUserAccountDependencies
import dagger.Component

@Component(dependencies = [AppProvider::class])
interface FeatureUserAccountDependenciesComponent : FeatureUserAccountDependencies

@Component(dependencies = [AppProvider::class, UserAccountProvider::class])
interface FeatureFeatureNewsFeedDependenciesComponent : FeatureNewsFeedDependencies