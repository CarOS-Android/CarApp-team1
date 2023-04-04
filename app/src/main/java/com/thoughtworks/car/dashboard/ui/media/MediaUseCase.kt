package com.thoughtworks.car.dashboard.ui.media

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.thoughtworks.car.R
import javax.inject.Inject

data class MediaUiState(
    @DrawableRes val image: Int = R.drawable.fake_media_player,
)

class MediaUseCase @Inject constructor() {
    val uiState by mutableStateOf(MediaUiState())
}
