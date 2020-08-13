package com.begoml.testfeatures.di.modules

import com.begoml.common_api.navigation.NavigationGlobal
import com.begoml.common_api.navigation.RouterGlobal
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create(Router())

    @Provides
    @Singleton
    @NavigationGlobal
    fun provideGlobalNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    @RouterGlobal
    fun provideGlobalRouter(cicerone: Cicerone<Router>): Router = cicerone.router

}