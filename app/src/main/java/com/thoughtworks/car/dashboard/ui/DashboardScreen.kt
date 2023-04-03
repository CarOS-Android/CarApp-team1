package com.thoughtworks.car.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.dashboard.DashboardViewModel
import com.thoughtworks.car.dashboard.ui.door.DoorView
import com.thoughtworks.car.dashboard.ui.navi.NaviView
import com.thoughtworks.car.ui.theme.Dimensions
import com.thoughtworks.car.ui.theme.Theme

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        DoorView(
            modifier = Modifier.weight(DOOR_VIEW_WEIGHT),
            doorUiState = uiState.doorUiState,
            toggleSeatDoors = { viewModel.doorUseCase.toggleSeatDoors() },
            toggleHoodDoor = { viewModel.doorUseCase.toggleHoodDoor() },
            toggleRearDoor = { viewModel.doorUseCase.toggleRearDoor() }
        )
        Spacer(modifier = Modifier.width(80.dp))
        NaviView(
            modifier = Modifier.weight(NAVI_VIEW_WEIGHT),
            naviUiState = uiState.naviUiState
        )
    }
}

private const val DOOR_VIEW_WEIGHT = 3f
private const val NAVI_VIEW_WEIGHT = 2f
