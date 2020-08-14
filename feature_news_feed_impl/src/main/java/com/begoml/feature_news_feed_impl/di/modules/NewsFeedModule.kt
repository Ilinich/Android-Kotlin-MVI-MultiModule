package com.begoml.feature_news_feed_impl.di.modules

import androidx.lifecycle.ViewModelProvider
import com.begoml.feature_news_feed_impl.presentation.news.NewsFeedViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface NewsFeedModule {

    @Binds
    fun bindsViewModelFactory(factory: NewsFeedViewModelFactory): ViewModelProvider.Factory
}