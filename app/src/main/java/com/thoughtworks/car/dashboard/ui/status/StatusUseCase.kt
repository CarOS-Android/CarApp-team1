package com.thoughtworks.car.dashboard.ui.status

import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.thoughtworks.car.R
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.dashboard.ui.brake.AutoHoldUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class StatusUiState(
    val windowLockState: Boolean = true,
)

class StatusUseCase @Inject constructor() {
    private val _uiState: MutableStateFlow<StatusUiState> = MutableStateFlow(StatusUiState())
    val uiState: StateFlow<StatusUiState> = _uiState

    fun toggleWindowLock() {
        Logger.i("toggle window lock")
    }
}
