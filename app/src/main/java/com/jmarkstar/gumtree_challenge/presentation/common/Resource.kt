package com.jmarkstar.gumtree_challenge.presentation.common

// A generic class that contains data and status about loading this data.
sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Error<T>(val message: String?, val throwable: Throwable? = null) : Resource<T>()
}
