package com.begoml.feature_news_feed_impl.domain


import com.begoml.tools.ResultWrapper
import com.begoml.tools.UseCase
import com.begoml.tools.errors.Failure
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) : UseCase<List<NewsModel>, UseCase.None>() {

    override suspend fun run(params: None): ResultWrapper<Failure, List<NewsModel>> = repository.getNews()
}