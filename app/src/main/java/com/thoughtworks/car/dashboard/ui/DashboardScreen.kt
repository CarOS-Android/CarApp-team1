package com.thoughtworks.car.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.dashboard.DashboardViewModel
import com.thoughtworks.car.dashboard.ui.brake.AutoHoldView
import com.thoughtworks.car.dashboard.ui.brake.ParkingBrakeView
import com.thoughtworks.car.dashboard.ui.door.DoorView
import com.thoughtworks.car.dashboard.ui.hvac.HvacView
import com.thoughtworks.car.dashboard.ui.ignition.IgnitionView
import com.thoughtworks.car.dashboard.ui.media.MediaView
import com.thoughtworks.car.dashboard.ui.navi.NaviView
import com.thoughtworks.car.dashboard.ui.status.StatusView
import com.thoughtworks.car.dashboard.ui.time.TimeView
import com.thoughtworks.car.dashboard.ui.voice.VoiceView
import com.thoughtworks.car.ui.theme.Dimensions
import com.thoughtworks.car.ui.theme.Theme

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        Column(modifier = Modifier.weight(DOOR_VIEW_WEIGHT)) {
            Row {
                TimeView(timeUiState = viewModel.timeUseCase.uiState)
                Spacer(modifier = Modifier.width(8.dp))
                VoiceView(voiceUiState = viewModel.voiceUseCase.uiState)
                Spacer(modifier = Modifier.weight(1f))
                IgnitionView(ignitionUiState = viewModel.ignitionUseCase.uiState)
            }
            DoorView(
                doorUiState = viewModel.doorUseCase.uiState,
                toggleSeatDoors = { viewModel.doorUseCase.toggleSeatDoors() },
                toggleHoodDoor = { viewModel.doorUseCase.toggleHoodDoor() },
                toggleRearDoor = { viewModel.doorUseCase.toggleRearDoor() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                HvacView()
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 40.dp)
                ) {
                    // hand brake view
                    ParkingBrakeView(
                        parkingBrakeUiState = viewModel.parkingBrakeUseCase.uiState,
                        toggleParkingBrake = {
                            viewModel.parkingBrakeUseCase.toggleParkingBrake()
                        }
                    )
                    AutoHoldView(
                        autoHoldUiState = viewModel.autoHoldUseCase.uiState,
                        toggleAutoHold = { viewModel.autoHoldUseCase.toggleAutoHold() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(80.dp))
        Column(modifier = Modifier.weight(NAVI_VIEW_WEIGHT)) {
            NaviView(naviUiState = viewModel.naviUseCase.uiState)
            Spacer(modifier = Modifier.height(8.dp))
            MediaView(mediaUiState = viewModel.mediaUseCase.uiState)
            Spacer(modifier = Modifier.height(8.dp))
            StatusView(statusUiState = viewModel.statusUseCase.uiState)
        }
    }
}

private const val DOOR_VIEW_WEIGHT = 3f
private const val NAVI_VIEW_WEIGHT = 2f
