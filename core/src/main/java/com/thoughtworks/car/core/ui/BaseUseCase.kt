package com.thoughtworks.car.core.ui

interface BaseUseCase {
    // do something to release resource or unregister callbacks
    fun onCleared()
}
