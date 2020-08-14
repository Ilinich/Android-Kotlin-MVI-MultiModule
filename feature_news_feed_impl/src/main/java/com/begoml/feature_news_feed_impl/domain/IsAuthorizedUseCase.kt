package com.begoml.feature_news_feed_impl.domain

import com.begoml.feature_user_account_api.UserAccountRepository
import com.begoml.tools.ResultWrapper
import com.begoml.tools.UseCase
import com.begoml.tools.errors.Failure
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(
    private val repository: UserAccountRepository
) : UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None): ResultWrapper<Failure, Boolean> {
        return repository.isAuthorized()
    }

}