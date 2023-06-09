package com.thoughtworks.car.core.network.entity

sealed class Result<out T : Any> {
    object Loading : Result<Nothing>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data class Success<out T : Any>(val data: T?) : Result<T>()
}