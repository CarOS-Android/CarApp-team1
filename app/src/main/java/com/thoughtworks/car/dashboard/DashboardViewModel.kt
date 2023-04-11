package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.brake.AutoHoldUseCase
import com.thoughtworks.car.dashboard.ui.brake.ParkingBrakeUseCase
import com.thoughtworks.car.dashboard.ui.door.DoorUseCase
import com.thoughtworks.car.dashboard.ui.ignition.IgnitionUseCase
import com.thoughtworks.car.dashboard.ui.media.MediaUseCase
import com.thoughtworks.car.dashboard.ui.navi.NaviUseCase
import com.thoughtworks.car.dashboard.ui.status.StatusUseCase
import com.thoughtworks.car.dashboard.ui.time.TimeUseCase
import com.thoughtworks.car.dashboard.ui.voice.VoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class DashBoardUiState(val centerAreaState: CenterAreaState = CenterAreaState.LOCK_CONTROLLER)

enum class CenterAreaState {
    LOCK_CONTROLLER,
    LIGHT_CONTROLLER
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val timeUseCase: TimeUseCase,
    val voiceUseCase: VoiceUseCase,
    val ignitionUseCase: IgnitionUseCase,
    val doorUseCase: DoorUseCase,
    val naviUseCase: NaviUseCase,
    val mediaUseCase: MediaUseCase,
    val statusUseCase: StatusUseCase,
    val autoHoldUseCase: AutoHoldUseCase,
    val parkingBrakeUseCase: ParkingBrakeUseCase
) : ViewModel() {

    private var _uiState: MutableStateFlow<DashBoardUiState> = MutableStateFlow(DashBoardUiState())

    val uiState: StateFlow<DashBoardUiState> = _uiState

    fun switchCenterAreaState() {
        _uiState.value = if (uiState.value.centerAreaState == CenterAreaState.LOCK_CONTROLLER) {
            DashBoardUiState(CenterAreaState.LIGHT_CONTROLLER)
        } else {
            DashBoardUiState(CenterAreaState.LOCK_CONTROLLER)
        }
    }

    override fun onCleared() {
        timeUseCase.onCleared()
        voiceUseCase.onCleared()
        ignitionUseCase.onCleared()
        doorUseCase.onCleared()
        naviUseCase.onCleared()
        mediaUseCase.onCleared()
        statusUseCase.onCleared()
        autoHoldUseCase.onCleared()
        parkingBrakeUseCase.onCleared()
        super.onCleared()
    }
}
