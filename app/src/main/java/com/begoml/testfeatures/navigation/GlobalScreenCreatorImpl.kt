package com.begoml.testfeatures.navigation

import com.begoml.common_api.navigation.GlobalScreenCreator
import com.begoml.feature_news_feed_impl.presentation.view.NewsFeedFlowFragment
import com.begoml.feature_user_account_impl.presentation.view.AccountFlowFragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class GlobalScreenCreatorImpl @Inject constructor() : GlobalScreenCreator {

    override val userAccountScreen: Screen
        get() = Screens.UserAccount

    override val newsFeedScreen: Screen
        get() = Screens.NewsFeed
}

sealed class Screens : SupportAppScreen() {

    object UserAccount : Screens() {
        override fun getFragment() = AccountFlowFragment()
    }

    object NewsFeed : Screens() {
        override fun getFragment() = NewsFeedFlowFragment()
    }
}