package com.begoml.tools.extension

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <A, B> Iterable<A>.parallelMap(action: suspend (A) -> B): List<B> = coroutineScope {
    map {
        async {
            action(it)
        }
    }.awaitAll()
}