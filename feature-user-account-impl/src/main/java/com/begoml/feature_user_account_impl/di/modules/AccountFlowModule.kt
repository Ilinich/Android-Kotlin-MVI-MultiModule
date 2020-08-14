package com.begoml.feature_user_account_impl.di.modules

import com.begoml.feature_user_account_api.UserAccountRepository
import com.begoml.feature_user_account_impl.data.UserAccountRepositoryImpl
import com.begoml.feature_user_account_impl.di.AccountFlowCoordinator
import com.begoml.feature_user_account_impl.navigation.FlowCoordinator
import com.begoml.feature_user_account_impl.navigation.AccountFlowCoordinatorImpl
import dagger.Binds
import dagger.Module

@Module
interface AccountFlowModule {

    @Binds
    @AccountFlowCoordinator
    fun bindsFlowCoordinator(accountFlowCoordinator: AccountFlowCoordinatorImpl): FlowCoordinator

    @Binds
    fun bindsUserAccountRepository(userAccountRepository: UserAccountRepositoryImpl): UserAccountRepository
}