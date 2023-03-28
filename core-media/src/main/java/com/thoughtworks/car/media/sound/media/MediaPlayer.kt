package com.thoughtworks.car.media.sound.media

interface MediaPlayer {
    fun play(mediaItem: MediaItem)

    fun pause()

    fun resume()

    fun stop()

    fun release()
}