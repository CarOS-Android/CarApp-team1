package com.thoughtworks.car.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.dashboard.CenterAreaState
import com.thoughtworks.car.dashboard.DashboardViewModel
import com.thoughtworks.car.dashboard.ui.brake.AutoHoldView
import com.thoughtworks.car.dashboard.ui.brake.ParkingBrakeView
import com.thoughtworks.car.dashboard.ui.door.DoorView
import com.thoughtworks.car.dashboard.ui.hvac.HvacView
import com.thoughtworks.car.dashboard.ui.ignition.IgnitionView
import com.thoughtworks.car.dashboard.ui.light.LightView
import com.thoughtworks.car.dashboard.ui.media.MediaView
import com.thoughtworks.car.dashboard.ui.navi.NaviView
import com.thoughtworks.car.dashboard.ui.status.StatusView
import com.thoughtworks.car.dashboard.ui.time.TimeView
import com.thoughtworks.car.dashboard.ui.voice.VoiceView
import com.thoughtworks.car.ui.theme.Theme
import com.thoughtworks.car.ui.R as CAR_UIR

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = dimensionResource(CAR_UIR.dimen.dimension_50))
    ) {
        Column(modifier = Modifier.weight(DOOR_VIEW_WEIGHT)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TimeView(timeUiState = viewModel.timeUseCase.uiState)
                Spacer(modifier = Modifier.width(dimensionResource(CAR_UIR.dimen.dimension_20)))
                VoiceView(voiceUiState = viewModel.voiceUseCase.uiState)
                Spacer(modifier = Modifier.padding(dimensionResource(CAR_UIR.dimen.dimension_90)))
                IgnitionView(ignitionUiState = viewModel.ignitionUseCase.uiState)
            }
            Box(
                modifier = Modifier
                    .padding(start = dimensionResource(CAR_UIR.dimen.dimension_123))
                    .width(dimensionResource(CAR_UIR.dimen.dimension_625))
                    .height(dimensionResource(CAR_UIR.dimen.dimension_530))
            ) {
                if (uiState.value.centerAreaState == CenterAreaState.LOCK_CONTROLLER) {
                    DoorView(
                        doorUiState = viewModel.doorUseCase.uiState,
                        toggleSeatDoors = { viewModel.doorUseCase.toggleSeatDoors() },
                        toggleHoodDoor = { viewModel.doorUseCase.toggleHoodDoor() },
                        toggleRearDoor = { viewModel.doorUseCase.toggleRearDoor() },
                        switchCenterAreaState = { viewModel.switchCenterAreaState() }
                    )
                } else {
                    LightView(
                        switchCenterAreaState = { viewModel.switchCenterAreaState() },
                        lightUiState = viewModel.lightUseCase.uiState,
                        toggleHazardLight = { viewModel.lightUseCase.toggleHazardLight() },
                        toggleHeadLight = { viewModel.lightUseCase.toggleHeadLight() },
                        toggleHighBeamLight = { viewModel.lightUseCase.toggleHighBeamLight() }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(dimensionResource(CAR_UIR.dimen.dimension_50)))
                HvacView()
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ParkingBrakeView(
                        parkingBrakeUiState = viewModel.parkingBrakeUseCase.uiState,
                        toggleParkingBrake = {
                            viewModel.parkingBrakeUseCase.toggleParkingBrake()
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AutoHoldView(
                        autoHoldUiState = viewModel.autoHoldUseCase.uiState,
                        toggleAutoHold = { viewModel.autoHoldUseCase.toggleAutoHold() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(dimensionResource(CAR_UIR.dimen.dimension_30)))
        Column(
            modifier = Modifier
                .weight(NAVI_VIEW_WEIGHT)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(CAR_UIR.dimen.dimension_26)))
            NaviView(naviUiState = viewModel.naviUseCase.uiState)
            MediaView(mediaUiState = viewModel.mediaUseCase.uiState)
            StatusView(
                statusUiState = viewModel.statusUseCase.uiState,
                toggleWindowLock = { viewModel.statusUseCase.toggleWindowLock() }
            )
        }
    }
}

private const val DOOR_VIEW_WEIGHT = 3f
private const val NAVI_VIEW_WEIGHT = 2f
