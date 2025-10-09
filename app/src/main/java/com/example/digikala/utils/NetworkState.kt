package com.example.digikala.utils

sealed class NetworkState<out T> {
    object Uninitialized: NetworkState<Nothing>()
    object Loading: NetworkState<Nothing>()
    data class Success<T>(val data: T): NetworkState<T>()
    data class UnSuccess<T>(val code: T): NetworkState<T>()
    data class Failure<T>(val throwable: Throwable): NetworkState<T>()
}