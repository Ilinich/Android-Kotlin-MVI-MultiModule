package com.begoml.feature_user_account_api.di

import com.begoml.feature_user_account_api.UserAccountRepository

interface UserAccountProvider {

    fun provideUserAccountRepository(): UserAccountRepository
}