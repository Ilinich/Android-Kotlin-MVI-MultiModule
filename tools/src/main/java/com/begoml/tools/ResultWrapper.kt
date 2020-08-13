package com.begoml.tools

import com.begoml.tools.ResultWrapper.Error
import com.begoml.tools.ResultWrapper.Success

sealed class ResultWrapper<out L, out R> {
    /** * Represents the left side of [ResultWrapper] class which by convention is a "Failure". */
    data class Error<out L>(val error: L) : ResultWrapper<L, Nothing>()

    /** * Represents the right side of [ResultWrapper] class which by convention is a "Success". */
    data class Success<out R>(val data: R) : ResultWrapper<Nothing, R>()

    val isRight get() = this is Success<R>
    val isLeft get() = this is Error<L>

    fun <L> error(a: L) = Error(a)
    fun <R> success(b: R) = Success(b)
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> ResultWrapper<L, R>.flatMap(fn: (R) -> ResultWrapper<L, T>): ResultWrapper<L, T> =
    when (this) {
        is Error -> Error(error)
        is Success -> fn(data)
    }

fun <T, L, R> ResultWrapper<L, R>.map(fn: (R) -> (T)): ResultWrapper<L, T> = this.flatMap(fn.c(::success))

fun <L, R, F> ResultWrapper<F, R>.mapResult(success: (R) -> L): ResultWrapper<F, L> {
    return when (this) {
        is Error -> this
        is Success -> Success(success(data))
    }
}

fun <L, R, F> ResultWrapper<F, R>.mapResults(success: (R) -> L, error: () -> F): ResultWrapper<F, L> {
    return when (this) {
        is Error -> Error(error())
        is Success -> Success(success(data))
    }
}

fun <L, R, T> ResultWrapper<L, R>.getResultWithProcessError(success: (R) -> T, error: () -> T): T {
    return when (this) {
        is Error -> error()
        is Success -> success(data)
    }
}

fun <L, R> ResultWrapper<L, R>.handleSuccessResult(success: (R) -> Any) {
    when (this) {
        is Success -> success(data)
    }
}

fun <L, R> ResultWrapper<L, R>.handleFailureResult(error: (L) -> Any) {
    when (this) {
        is Error -> error(error)
    }
}

fun <L, R> ResultWrapper<L, R>.handleResults(error: (L) -> Any, success: (R) -> Any) {
    when (this) {
        is Error -> error(error)
        is Success -> success(data)
    }
}