package com.begoml.presentation.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

/**
 * intermediary between Model and View that decides how to handle Events,
 * supply State updates, and new News
 */
abstract class MviViewModel<ViewState, UiEvent, Command, Effect, News>(
    viewState: ViewState,
    private val eventHandler: EventHandler<UiEvent, Command>,
    private val actor: Actor<ViewState, Command, Effect>,
    private val reducer: Reducer<ViewState, Effect>,
    private val postProcessor: PostProcessor<ViewState, Effect, Command>? = null,
    private val newsPublisher: NewsPublisher<ViewState, Effect, News>? = null,
    bootstrapper: Bootstrapper<Command>? = null
) : ViewModel() {

    private val newsLiveData: LiveEvent<News> by lazy {
        LiveEvent<News>().apply {
            value = null
        }
    }

    private val state: MutableLiveData<ViewState> by lazy {
        MutableLiveData<ViewState>().apply {
            value = viewState
        }
    }

    /**
     * "persistent" data about the UI,
     *  which should be shown even after recreating the View
     */
    open val viewState: LiveData<ViewState> by lazy {
        state
    }

    /**
     * send for single live event
     */
    open val news: LiveEvent<News> by lazy {
        newsLiveData
    }

    /**
     * for side effects processing
     */
    private val effectLiveData: MutableLiveData<Effect> by lazy { MutableLiveData<Effect>() }

    init {
        effectLiveData.observeForever { effect ->
            nextEffect(effect)
        }

        bootstrapper?.let { bootstrapper ->
            bootstrapper { command ->
                nextCommand(command)
            }
        }
    }

    /**
     *
     * @param event - ui intent
     */
    fun dispatchEvent(event: UiEvent) {
        val command = eventHandler(event)
        nextCommand(command)
    }

    private fun nextCommand(command: Command) {
        actor(state.state(), command, viewModelScope) { effect ->
            nextEffect(effect)
        }
    }

    private fun nextEffect(effect: Effect) {
        val viewState = reducer(state.state(), effect)
        nextViewState(viewState)

        postProcessor?.let { postProcessor ->
            postProcessor(state.state(), effect)?.let { command ->
                nextCommand(command)
            }
        }

        newsPublisher?.let { newsPublisher ->
            newsPublisher(state.state(), effect)?.let { news ->
                nextNews(news)
            }
        }
    }

    private fun nextViewState(viewState: ViewState) {
        state.value = viewState
    }

    private fun nextNews(news: News) {
        newsLiveData.value = news
    }
}

private fun <T> LiveData<T>.state() = value!!

typealias EventHandler<Event, Command> = (event: Event) -> Command

typealias Actor<ViewState, Command, Effect> = (state: ViewState, command: Command, viewModelScope: CoroutineScope, sendEffect: (effect: Effect) -> Unit) -> Unit

typealias Reducer<ViewState, Effect> = (state: ViewState, effect: Effect) -> ViewState

typealias NewsPublisher<ViewState, Effect, News> = (state: ViewState, effect: Effect) -> News?

typealias PostProcessor<ViewState, Effect, Command> = (state: ViewState, effect: Effect) -> Command?

typealias Bootstrapper<Command> = (sendCommand: (command: Command) -> Unit) -> Unit
