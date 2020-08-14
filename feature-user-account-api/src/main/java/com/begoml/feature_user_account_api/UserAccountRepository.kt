package com.begoml.feature_user_account_api

import com.begoml.feature_user_account_api.model.Account
import com.begoml.tools.ResultWrapper
import com.begoml.tools.errors.Failure

interface UserAccountRepository {
    suspend fun getAccount(): ResultWrapper<Failure, Account>

    fun isAuthorized(): ResultWrapper<Failure, Boolean>
}