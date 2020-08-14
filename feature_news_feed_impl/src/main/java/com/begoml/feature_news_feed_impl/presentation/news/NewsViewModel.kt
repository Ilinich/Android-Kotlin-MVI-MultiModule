package com.begoml.feature_news_feed_impl.presentation.news

import androidx.lifecycle.SavedStateHandle
import com.begoml.feature_news_feed_impl.domain.GetNewsUseCase
import com.begoml.feature_news_feed_impl.domain.NewsModel
import com.begoml.feature_news_feed_impl.presentation.news.NewsViewModel.Companion.SAVED_STATE_KEY_NEWS
import com.begoml.presentation.mvi.*
import com.begoml.tools.UseCase
import com.begoml.tools.handleResults
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NewsViewModel(
    savedStateHandle: SavedStateHandle,
    reducer: ReducerImpl,
    actor: ActorImpl,
    postProcessor: PostProcessorImpl,
    bootstrapper: BootstrapperImpl
) : MviViewModel<ViewState, Event, Command, Effect, News>(
    viewState = ViewState(),
    eventHandler = UiEventTransformer(),
    actor = actor,
    reducer = reducer,
    postProcessor = postProcessor,
    bootstrapper = bootstrapper,
    newsPublisher = NewsPublisherImpl()
) {

    init {
        savedStateHandle.apply {
            val newsList = get<List<NewsModel>>(SAVED_STATE_KEY_NEWS) ?: emptyList()
            dispatchEvent(Event.UpdateState(newsList))
        }
    }

    companion object {
        const val SAVED_STATE_KEY_NEWS = "SAVED_STATE_KEY_NEWS"
    }
}

data class ViewState(
    val isLoading: Boolean = false,
    val newsList: List<NewsModel> = emptyList()
)

class ReducerImpl @Inject constructor() : Reducer<ViewState, Effect> {
    override fun invoke(state: ViewState, effect: Effect): ViewState {
        return when (effect) {
            Effect.StartedLoading -> state.copy(isLoading = true)
            Effect.StoppedLoading -> state.copy(isLoading = false)
            is Effect.NewsLoaded -> state.copy(newsList = effect.newsList)
            is Effect.StateUpdated -> state.copy(newsList = effect.newsList)
            else -> state.copy()
        }
    }
}

class ActorImpl constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getNewsUseCase: GetNewsUseCase
) : Actor<ViewState, Command, Effect> {

    override fun invoke(state: ViewState, command: Command, viewModelScope: CoroutineScope, sendEffect: (effect: Effect) -> Unit) {
        when (command) {
            Command.StartLoadData -> {
                updateData(viewModelScope, sendEffect)
            }
            Command.UpdateData -> {
                updateData(viewModelScope, sendEffect)
            }
            Command.SaveInstanceState -> {
                savedStateHandle.set(SAVED_STATE_KEY_NEWS, state.newsList)
            }
            is Command.UpdateState -> {
                sendEffect(Effect.StateUpdated(command.newsList))
            }
            is Command.NewsModelClicked -> {
                sendEffect(Effect.NewsModelClicked(command.newsModel))
            }
        }
    }

    private fun updateData(viewModelScope: CoroutineScope, sendEffect: (effect: Effect) -> Unit) {
        sendEffect(Effect.StartedLoading)

        getNewsUseCase(viewModelScope, viewModelScope, UseCase.None) {
            it.handleResults(
                {
                    sendEffect(Effect.StoppedLoading)
                }, { newsList ->
                    sendEffect(Effect.StoppedLoading)
                    sendEffect(Effect.NewsLoaded(newsList))
                }
            )
        }
    }
}

class PostProcessorImpl @Inject constructor() : PostProcessor<ViewState, Effect, Command> {
    override fun invoke(state: ViewState, effect: Effect): Command? {
        return when (effect) {
            else -> null
        }
    }
}

class BootstrapperImpl @Inject constructor() : Bootstrapper<Command> {

    override fun invoke(sendCommand: (command: Command) -> Unit) {
        sendCommand(Command.StartLoadData)
    }
}

class UiEventTransformer : EventHandler<Event, Command> {
    override fun invoke(event: Event): Command {
        return when (event) {
            Event.SaveInstanceState -> Command.SaveInstanceState
            is Event.UpdateState -> Command.UpdateState(event.newsList)
            is Event.NewsModelClick -> Command.NewsModelClicked(event.newsModel)
        }
    }
}

class NewsPublisherImpl : NewsPublisher<ViewState, Effect, News> {

    override fun invoke(state: ViewState, effect: Effect): News? = when (effect) {
        is Effect.NewsModelClicked -> News.GoToNewsDetails(effect.newsModel)
        else -> null
    }

}

sealed class Effect {

    object StartedLoading : Effect()
    object StoppedLoading : Effect()

    data class NewsLoaded(val newsList: List<NewsModel>) : Effect()
    data class StateUpdated(val newsList: List<NewsModel>) : Effect()
    data class NewsModelClicked(val newsModel: NewsModel) : Effect()
}

sealed class Event {

    data class NewsModelClick(val newsModel: NewsModel) : Event()

    object SaveInstanceState : Event()
    data class UpdateState(val newsList: List<NewsModel>) : Event()
}

sealed class News {
    data class GoToNewsDetails(val newsModel: NewsModel) : News()
}

sealed class Command {

    data class NewsModelClicked(val newsModel: NewsModel) : Command()

    object StartLoadData : Command()
    object UpdateData : Command()
    object SaveInstanceState : Command()
    data class UpdateState(val newsList: List<NewsModel>) : Command()

}