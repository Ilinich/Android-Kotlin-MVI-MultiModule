package com.begoml.feature_news_feed_impl.navigation

import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.feature_news_feed_impl.presentation.view.NewsFeedFragment
import com.begoml.presentation.di.RouterFlow
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class NewsFeedFlowCoordinatorImpl @Inject constructor(
    private val appFlowCoordinator: AppFlowCoordinator,
    @RouterFlow private val flowRouter: Router,
    private val proxyInjector: FeatureProxyInjector
) : FlowCoordinator {

    override fun setLaunchScreen() {
        flowRouter.newRootScreen(Screens.NewsFeed)
    }

    override fun goToUserAccount() {
        appFlowCoordinator.goToUserAccountFeature()
    }

    fun finishFeature() {
        proxyInjector.clearNewsFeedFeature()
        appFlowCoordinator.finishFeature()
    }

}

interface FlowCoordinator {

    fun setLaunchScreen()
    fun goToUserAccount()
}

sealed class Screens : SupportAppScreen() {

    object NewsFeed : Screens() {
        override fun getFragment() = NewsFeedFragment()
    }
}