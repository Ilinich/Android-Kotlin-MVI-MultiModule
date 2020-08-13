package com.begoml.presentation.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class FeatureNavigationModule {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create(Router()) }

    @Provides
    fun provideFlowCicerone(): Cicerone<Router> = cicerone

    @Provides
    @NavigationFlow
    fun provideFlowNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    @Provides
    @RouterFlow
    fun provideFlowRouter(cicerone: Cicerone<Router>): Router {

        return cicerone.router
    }
}