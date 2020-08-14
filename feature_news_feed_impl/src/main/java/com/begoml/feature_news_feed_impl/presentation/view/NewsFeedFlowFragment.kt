package com.begoml.feature_news_feed_impl.presentation.view

import android.os.Bundle
import android.view.View
import com.begoml.feature_news_feed_impl.di.NewsFeedFlowCoordinator
import com.begoml.feature_news_feed_impl.di.component.NewsFeedFlowComponent
import com.begoml.feature_news_feed_impl.navigation.FlowCoordinator
import com.begoml.presentation.di.DaggerComponentListener
import com.begoml.presentation.extension.addDaggerComponentLifecycle
import com.begoml.presentation.view.FlowFragment
import javax.inject.Inject

class NewsFeedFlowFragment : FlowFragment(), DaggerComponentListener<NewsFeedFlowComponent> {

    @Inject
    @field:[NewsFeedFlowCoordinator]
    lateinit var flowCoordinator: FlowCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        addDaggerComponentLifecycle<NewsFeedFlowComponent>()

        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            flowCoordinator.setLaunchScreen()
        }
    }

    override fun injectComponent(component: NewsFeedFlowComponent) {
        component.inject(this)
    }

    override fun createComponent(): NewsFeedFlowComponent {
        featureProxyInjector.injectNewsFeedFeature()
        return NewsFeedFlowComponent.get()
    }
}