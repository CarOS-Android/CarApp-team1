package com.thoughtworks.car.media.sound.tts

interface TTSSpeaker {
    fun play(ttsItem: TTSItem)

    fun stop()

    fun release()
}