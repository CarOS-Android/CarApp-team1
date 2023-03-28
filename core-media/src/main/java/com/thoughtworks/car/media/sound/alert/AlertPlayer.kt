package com.thoughtworks.car.media.sound.alert

interface AlertPlayer {
    fun prepareSounds(alertItems: List<AlertItem>)

    fun play(alertItem: AlertItem)

    fun pause()

    fun resume()

    fun stop()

    fun release()
}