package com.begoml.feature_news_feed_impl.di.component

import com.begoml.feature_news_feed_impl.di.deps.FeatureNewsFeedDependencies
import com.begoml.feature_news_feed_impl.di.modules.NewsFeedFlowModule
import com.begoml.feature_news_feed_impl.presentation.view.NewsFeedFlowFragment
import com.begoml.presentation.di.FeatureNavigationModule
import com.begoml.presentation.exception.InitComponentException
import dagger.Component

@Component(
    modules = [
        NewsFeedFlowModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [FeatureNewsFeedDependencies::class]
)
interface NewsFeedFlowComponent {

    fun inject(fragment: NewsFeedFlowFragment)

    companion object {

        private var component: NewsFeedFlowComponent? = null

        fun init(dependencies: FeatureNewsFeedDependencies) {
            component ?: DaggerNewsFeedFlowComponent.builder()
                .featureNewsFeedDependencies(dependencies)
                .build().apply {
                    component = this
                }
        }

        fun get(): NewsFeedFlowComponent = component ?: throw InitComponentException()

        fun clear() {
            component = null
        }
    }
}