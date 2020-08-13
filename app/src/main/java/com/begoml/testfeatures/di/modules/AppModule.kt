package com.begoml.testfeatures.di.modules

import com.begoml.testfeatures.FeatureProxyInjectorImpl
import com.begoml.testfeatures.navigation.AppFlowCoordinatorImpl
import com.begoml.testfeatures.navigation.GlobalScreenCreatorImpl
import com.begoml.testfeatures.tools.NetworkProviderImpl
import com.begoml.testfeatures.tools.ResourceProviderImpl
import com.begoml.common_api.FeatureProxyInjector
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.common_api.navigation.GlobalScreenCreator
import com.begoml.common_api.providers.NetworkProvider
import com.begoml.common_api.providers.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindsResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider

    @Binds
    @Singleton
    fun bindsFeatureProxyInjector(injector: FeatureProxyInjectorImpl): FeatureProxyInjector

    @Binds
    @Singleton
    fun bindsNetworkProvider(networkManager: NetworkProviderImpl): NetworkProvider

    @Binds
    @Singleton
    fun bindsAppFlowCoordinator(appFlowCoordinator: AppFlowCoordinatorImpl): AppFlowCoordinator

    @Binds
    @Singleton
    fun bindsGlobalScreenCreator(screenCreator: GlobalScreenCreatorImpl): GlobalScreenCreator

}