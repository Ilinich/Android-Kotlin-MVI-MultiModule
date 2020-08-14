package com.begoml.feature_news_feed_impl.domain

import com.begoml.tools.ResultWrapper
import com.begoml.tools.errors.Failure

interface NewsFeedRepository {

    suspend fun getNews(): ResultWrapper<Failure, List<NewsModel>>
}