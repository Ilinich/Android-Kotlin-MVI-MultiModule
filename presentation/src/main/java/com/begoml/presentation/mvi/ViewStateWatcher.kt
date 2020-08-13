package com.begoml.presentation.mvi

class ViewStateWatcher<T> private constructor(
    private val watchers: List<Watcher<T, Any?>>
) {
    private var model: T? = null

    fun render(newModel: T) {
        val oldModel = model
        watchers.forEach { element ->
            val getter = element.accessor
            val new = getter(newModel)
            if (oldModel == null || element.diffStrategy(getter(oldModel), new)) {
                element.callback(new)
            }
        }

        model = newModel
    }

    private class Watcher<T, R>(
        val accessor: (T) -> R,
        val callback: (R) -> Unit,
        val diffStrategy: DiffStrategy<R>
    )

    /**
     * It's obligatory to clear watcher in onDestroyView
     */
    fun clear() {
        model = null
    }

    class Builder<T> @PublishedApi internal constructor() {
        private val watchers = mutableListOf<Watcher<T, Any?>>()

        private fun <R> watch(
            accessor: (T) -> R,
            diff: DiffStrategy<R> = byValue(),
            callback: (R) -> Unit
        ) {
            watchers += Watcher(
                accessor,
                callback,
                diff
            ) as Watcher<T, Any?>
        }

        /*
         * Syntactic sugar around watch (scoped inside the builder)
         */

        operator fun <R> ((T) -> R).invoke(callback: (R) -> Unit) {
            watch(this, callback = callback)
        }

        operator fun <R> (DiffStrategy<R>).invoke(callback: (R) -> Unit) =
            this to callback

        @PublishedApi
        internal fun build(): ViewStateWatcher<T> =
            ViewStateWatcher(watchers)
    }
}

inline fun <T> initializeViewStateWatcher(init: ViewStateWatcher.Builder<T>.() -> Unit): ViewStateWatcher<T> =
    ViewStateWatcher.Builder<T>()
        .apply(init)
        .build()

/**
 * @return true if the values are different, false otherwise
 */
typealias DiffStrategy<T> = (T, T) -> Boolean

fun <T> byValue(): DiffStrategy<T> = { p1, p2 -> p2 != p1 }