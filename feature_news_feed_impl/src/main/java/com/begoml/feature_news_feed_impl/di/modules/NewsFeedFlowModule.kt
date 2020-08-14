package com.begoml.feature_news_feed_impl.di.modules

import com.begoml.feature_news_feed_impl.data.NewsFeedRepositoryImpl
import com.begoml.feature_news_feed_impl.di.NewsFeedFlowCoordinator
import com.begoml.feature_news_feed_impl.domain.NewsFeedRepository
import com.begoml.feature_news_feed_impl.navigation.FlowCoordinator
import com.begoml.feature_news_feed_impl.navigation.NewsFeedFlowCoordinatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NewsFeedFlowModule {

    @Binds
    @NewsFeedFlowCoordinator
    fun bindsFlowCoordinator(newsFeedFlowCoordinator: NewsFeedFlowCoordinatorImpl): FlowCoordinator

    @Binds
    fun bindsNewsFeedRepository(newsFeedRepository: NewsFeedRepositoryImpl): NewsFeedRepository
}