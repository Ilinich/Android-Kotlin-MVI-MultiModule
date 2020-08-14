package com.begoml.common_api.navigation

import ru.terrakok.cicerone.Screen

interface GlobalScreenCreator {

    val userAccountScreen: Screen

    val newsFeedScreen: Screen
}