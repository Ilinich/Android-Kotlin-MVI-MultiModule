package com.begoml.feature_user_account_impl.domain

import com.begoml.feature_user_account_api.UserAccountRepository
import com.begoml.feature_user_account_api.model.Account
import com.begoml.tools.ResultWrapper
import com.begoml.tools.UseCase
import com.begoml.tools.errors.Failure
import javax.inject.Inject

class UserAccountUseCase @Inject constructor(
    private val userAccountRepository: UserAccountRepository
) : UseCase<Account, Any>() {

    override suspend fun run(params: Any): ResultWrapper<Failure, Account> {
        return userAccountRepository.getAccount()
    }
}