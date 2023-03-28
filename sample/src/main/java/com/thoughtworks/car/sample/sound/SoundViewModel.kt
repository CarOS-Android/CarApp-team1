package com.thoughtworks.car.sample.sound

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.media.sound.alert.AlertItem
import com.thoughtworks.car.media.sound.alert.AlertPlayer
import com.thoughtworks.car.media.sound.media.MediaItem
import com.thoughtworks.car.media.sound.media.MediaPlayer
import com.thoughtworks.car.media.sound.tts.TTSItem
import com.thoughtworks.car.media.sound.tts.TTSSpeaker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SoundViewModel @Inject constructor(
    private val alertPlayer: AlertPlayer,
    private val mediaPlayer: MediaPlayer,
    private val ttsSpeaker: TTSSpeaker
) : ViewModel() {

    fun playAlert(alertItem: AlertItem) {
        alertPlayer.play(alertItem)
    }

    fun playMedia(mediaItem: MediaItem) {
        mediaPlayer.play(mediaItem)
    }

    fun playTts(ttsItem: TTSItem) {
        ttsSpeaker.play(ttsItem)
    }

    override fun onCleared() {
        super.onCleared()
        alertPlayer.release()
        mediaPlayer.release()
        ttsSpeaker.release()
    }
}