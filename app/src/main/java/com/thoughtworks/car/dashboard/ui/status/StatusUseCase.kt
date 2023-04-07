package com.thoughtworks.car.dashboard.ui.status

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.thoughtworks.car.R
import com.thoughtworks.car.core.ui.BaseUseCase
import javax.inject.Inject

data class StatusUiState(
    @DrawableRes val image: Int = R.drawable.fake_system_status,
)

class StatusUseCase @Inject constructor() : BaseUseCase {
    val uiState by mutableStateOf(StatusUiState())

    override fun onCleared() {
        // do nothing
    }
}
