package com.thoughtworks.car.dashboard.ui.voice

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.thoughtworks.car.R
import javax.inject.Inject

data class VoiceUiState(
    @DrawableRes val voiceAssist: Int = R.drawable.fake_voice_assist,
    @DrawableRes val systemVolume: Int = R.drawable.fake_system_volume,
)

class VoiceUseCase @Inject constructor() {
    val uiState by mutableStateOf(VoiceUiState())
}
