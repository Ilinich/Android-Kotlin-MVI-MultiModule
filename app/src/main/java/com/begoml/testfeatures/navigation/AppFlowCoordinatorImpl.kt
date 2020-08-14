package com.begoml.testfeatures.navigation

import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.common_api.navigation.GlobalScreenCreator
import com.begoml.common_api.navigation.RouterGlobal
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppFlowCoordinatorImpl @Inject constructor(
    private val screenCreator: GlobalScreenCreator,
    @RouterGlobal private val globalRouter: Router
) : AppFlowCoordinator {

    override fun startApp() {
        val screen = screenCreator.newsFeedScreen
        globalRouter.newRootScreen(screen)
    }

    override fun finishFeature() {
        globalRouter.exit()
    }
}