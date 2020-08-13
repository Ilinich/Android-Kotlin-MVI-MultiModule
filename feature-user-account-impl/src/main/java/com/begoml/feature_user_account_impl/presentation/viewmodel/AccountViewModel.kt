package com.begoml.feature_user_account_impl.presentation.viewmodel

import com.begoml.presentation.exception.NotImplementedException
import com.begoml.presentation.mvi.*

class AccountViewModel : MviViewModel<ViewState, Event, Command, Effect, News>(
    viewState = ViewState(),
    eventHandler = UiEventTransformer(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    bootstrapper = BootstrapperImpl()
) {

}

data class ViewState(
    val isLoading: Boolean = false
)

class ReducerImpl : Reducer<ViewState, Effect> {
    override fun invoke(state: ViewState, effect: Effect): ViewState {
        return when (effect) {
            Effect.StartedLoading -> state.copy(isLoading = true)
            Effect.StoppedLoading -> state.copy(isLoading = false)
            else -> state.copy()
        }
    }
}

class ActorImpl : Actor<ViewState, Command, Effect> {

    override fun invoke(state: ViewState, command: Command, sendEffect: (effect: Effect) -> Unit) {
        when (command) {

            Command.StartLoadData -> {
                sendEffect(Effect.StartedLoading)
            }

            Command.UpdateData -> {
                sendEffect(Effect.StartedLoading)

                // todo do some work

                sendEffect(Effect.StoppedLoading)
            }
        }
    }
}

class PostProcessorImpl : PostProcessor<ViewState, Effect, Command> {
    override fun invoke(state: ViewState, effect: Effect): Command? {
        return when (effect) {
            else -> null
        }
    }
}

class BootstrapperImpl : Bootstrapper<Command> {

    override fun invoke(sendCommand: (command: Command) -> Unit) {
        sendCommand(Command.UpdateData)
    }
}

class UiEventTransformer : EventHandler<Event, Command> {
    override fun invoke(event: Event): Command {
        throw NotImplementedException()
    }
}

sealed class Effect {

    object StartedLoading : Effect()
    object StoppedLoading : Effect()

    object SavedStateHandle : Effect()

}

sealed class Event {

}

sealed class News {

}

sealed class Command {

    object StartLoadData : Command()
    object UpdateData : Command()

}