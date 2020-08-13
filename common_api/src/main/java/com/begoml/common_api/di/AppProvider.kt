package com.begoml.common_api.di

import android.content.Context
import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.common_api.navigation.GlobalScreenCreator
import com.begoml.common_api.navigation.NavigationGlobal
import com.begoml.common_api.providers.ResourceProvider
import com.begoml.datasource_api.local.LocalAccountDataSource
import com.begoml.datasource_api.remote.RemoteAccountDataSource
import ru.terrakok.cicerone.NavigatorHolder

interface AppProvider {

    fun provideContext(): Context

    fun provideInjector(): FeatureProxyInjector

    fun provideResourceManager(): ResourceProvider

    fun provideAppFlowCoordinator(): AppFlowCoordinator

    fun provideScreenCreator(): GlobalScreenCreator

    @NavigationGlobal
    fun provideNavigatorHolder(): NavigatorHolder

    fun provideRemoteAccountDataSource(): RemoteAccountDataSource

    fun provideLocalAccountDataSource(): LocalAccountDataSource
}