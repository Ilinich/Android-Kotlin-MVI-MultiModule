package com.begoml.feature_news_feed_impl.presentation.news

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.begoml.feature_news_feed_impl.domain.GetNewsUseCase
import javax.inject.Inject

class NewsFeedViewModelFactory @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    fragment: Fragment,
    private val reducer: ReducerImpl,
    private val postProcessor: PostProcessorImpl,
    private val bootstrapper: BootstrapperImpl
) : AbstractSavedStateViewModelFactory(fragment as SavedStateRegistryOwner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return NewsViewModel(
            savedStateHandle = handle,
            reducer = reducer,
            actor = ActorImpl(
                getNewsUseCase = getNewsUseCase,
                savedStateHandle = handle
            ),
            postProcessor = postProcessor,
            bootstrapper = bootstrapper
        ) as T
    }
}