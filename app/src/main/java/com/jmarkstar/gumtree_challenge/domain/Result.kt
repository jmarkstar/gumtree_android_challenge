package com.jmarkstar.gumtree_challenge.domain

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure(
        val throwable: Throwable?
    ) : ResultOf<Nothing>()
}

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (throwable: Throwable?) -> Unit) {
    if (this is ResultOf.Failure) {
        callback(throwable)
    }
}

inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is ResultOf.Success) {
        callback(value)
    }
}

inline fun <reified T, reified R> ResultOf<T>.map(transform: (T) -> R): ResultOf<R> {
    return when (this) {
        is ResultOf.Success -> ResultOf.Success(transform(value))
        is ResultOf.Failure -> this
    }
}

inline fun <T> ResultOf<T>.withDefault(value: () -> T): ResultOf.Success<T> {
    return when (this) {
        is ResultOf.Success -> this
        is ResultOf.Failure -> ResultOf.Success(value())
    }
}
