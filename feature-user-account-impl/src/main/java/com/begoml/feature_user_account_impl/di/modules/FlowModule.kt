package com.begoml.feature_user_account_impl.di.modules

import com.begoml.feature_user_account_impl.navigation.FlowCoordinator
import com.begoml.feature_user_account_impl.navigation.FlowCoordinatorImpl
import dagger.Binds
import dagger.Module

@Module
interface FlowModule {

    @Binds
    fun bindsFlowCoordinator(flowCoordinator: FlowCoordinatorImpl): FlowCoordinator
}