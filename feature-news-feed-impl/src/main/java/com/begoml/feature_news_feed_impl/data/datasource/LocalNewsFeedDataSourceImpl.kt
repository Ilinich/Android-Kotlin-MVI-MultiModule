package com.begoml.feature_news_feed_impl.data.datasource

import com.begoml.feature_news_feed_impl.domain.NewsModel
import com.begoml.tools.ResultWrapper
import com.begoml.tools.errors.Failure
import javax.inject.Inject

class LocalNewsFeedDataSourceImpl @Inject constructor() : LocalNewsFeedDataSource {

    override suspend fun getNews(): ResultWrapper<Failure, List<NewsModel>> {
        return ResultWrapper.Success(
            listOf(
                NewsModel(
                    id = 1,
                    title = "Inline classes",
                    description = "Sometimes it is necessary for business logic to create a wrapper around some type. However, it introduces runtime overhead due to additional heap allocations. Moreover, if the wrapped type is primitive, the performance hit is terrible, because primitive types are usually heavily optimized by the runtime, while their wrappers don't get any special treatment."
                ),
                NewsModel(
                    id = 2,
                    title = "Data Classes",
                    description = "We frequently create classes whose main purpose is to hold data. In such a class some standard functionality and utility functions are often mechanically derivable from the data. "
                ),
                NewsModel(
                    id = 3,
                    title = "Sealed Classes",
                    description = "Sealed classes are used for representing restricted class hierarchies, when a value can have one of the types from a limited set, but cannot have any other type. They are, in a sense, an extension of enum classes: the set of values for an enum type is also restricted, but each enum constant exists only as a single instance, whereas a subclass of a sealed class can have multiple instances which can contain state."
                ),
                NewsModel(
                    id = 4,
                    title = "Inner Classes",
                    description = "A nested class marked as inner can access the members of its outer class. Inner classes carry a reference to an object of an outer class."
                )
            )
        )
    }
}

interface LocalNewsFeedDataSource {
    suspend fun getNews(): ResultWrapper<Failure, List<NewsModel>>
}