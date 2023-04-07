package com.thoughtworks.car.dashboard.ui.media

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.thoughtworks.car.R
import com.thoughtworks.car.core.ui.BaseUseCase
import javax.inject.Inject

data class MediaUiState(
    @DrawableRes val image: Int = R.drawable.fake_media_player,
)

class MediaUseCase @Inject constructor() : BaseUseCase {
    val uiState by mutableStateOf(MediaUiState())

    override fun onCleared() {
        // do nothing
    }
}
