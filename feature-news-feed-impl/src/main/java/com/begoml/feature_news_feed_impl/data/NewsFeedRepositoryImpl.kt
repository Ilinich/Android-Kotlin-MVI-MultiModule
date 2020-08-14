package com.begoml.feature_news_feed_impl.data

import com.begoml.feature_news_feed_impl.data.datasource.LocalNewsFeedDataSource
import com.begoml.feature_news_feed_impl.domain.NewsFeedRepository
import com.begoml.feature_news_feed_impl.domain.NewsModel
import com.begoml.tools.ResultWrapper
import com.begoml.tools.errors.Failure
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsFeedRepositoryImpl @Inject constructor(
    private val localNewsFeedDataSource: LocalNewsFeedDataSource
) : NewsFeedRepository {

    override suspend fun getNews(): ResultWrapper<Failure, List<NewsModel>> {
        delay(3000)
        return localNewsFeedDataSource.getNews()
    }
}