package com.begoml.feature_user_account_impl.data

import com.begoml.feature_user_account_api.UserAccountRepository
import com.begoml.feature_user_account_api.model.Account
import com.begoml.tools.ResultWrapper
import com.begoml.tools.errors.Failure
import javax.inject.Inject

class UserAccountRepositoryImpl @Inject constructor() : UserAccountRepository {

    override suspend fun getAccount(): ResultWrapper<Failure, Account> {
        return ResultWrapper.Success(Account(1))
    }

    override fun isAuthorized(): ResultWrapper<Failure, Boolean> {
        return ResultWrapper.Success(true)
    }
}