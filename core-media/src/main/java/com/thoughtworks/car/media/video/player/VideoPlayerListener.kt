package com.thoughtworks.car.media.video.player

interface VideoPlayerListener {
    fun onIsLoadingChanged(isLoading: Boolean) {}

    fun onPlayStateChanged(state: Int) {}
}