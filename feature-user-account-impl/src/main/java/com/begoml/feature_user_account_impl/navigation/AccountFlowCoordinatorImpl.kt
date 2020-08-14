package com.begoml.feature_user_account_impl.navigation

import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.feature_user_account_impl.presentation.view.AccountFlowFragment
import com.begoml.feature_user_account_impl.presentation.view.AccountFragment
import com.begoml.presentation.di.RouterFlow
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class AccountFlowCoordinatorImpl @Inject constructor(
    private val appFlowCoordinator: AppFlowCoordinator,
    @RouterFlow private val flowRouter: Router,
    private val proxyInjector: FeatureProxyInjector
) : FlowCoordinator {

    override fun setLaunchScreen() {
        flowRouter.newRootScreen(Screens.UserAccount)
    }

    fun finishFeature() {
        proxyInjector.clearUserAccountFeature()
        appFlowCoordinator.finishFeature()
    }

}

interface FlowCoordinator {

    fun setLaunchScreen()
}

sealed class Screens : SupportAppScreen() {

    object UserAccount : Screens() {
        override fun getFragment() = AccountFragment()
    }
}