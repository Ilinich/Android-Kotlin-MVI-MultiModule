package com.begoml.feature_news_feed_impl.di.component

import androidx.fragment.app.Fragment
import com.begoml.feature_news_feed_impl.di.modules.NewsFeedModule
import com.begoml.feature_news_feed_impl.presentation.view.NewsFeedFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Component(
    modules = [NewsFeedModule::class],
    dependencies = [NewsFeedFlowComponentProvider::class]
)
interface NewsFeedComponent {

    fun inject(fragment: NewsFeedFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun screen(screen: Fragment): Builder

        fun build(): NewsFeedComponent
        fun newsFeedFlowComponentProvider(newsFeedFlowComponentProvider: NewsFeedFlowComponentProvider): Builder
    }
}