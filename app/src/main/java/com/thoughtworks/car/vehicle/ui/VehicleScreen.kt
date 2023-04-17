package com.thoughtworks.car.vehicle.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.ui.theme.Theme
import com.thoughtworks.car.vehicle.VehicleViewModel
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionControlView
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionPanelView
import com.thoughtworks.car.vehicle.ui.aircondition.FragranceView
import com.thoughtworks.car.vehicle.ui.atmospherelight.AtmosphereLightView
import com.thoughtworks.car.vehicle.ui.seat.SeatUiState
import com.thoughtworks.car.vehicle.ui.seat.SeatView
import kotlinx.coroutines.flow.MutableStateFlow
import com.thoughtworks.car.ui.R as CAR_UIR

@Composable
fun VehicleScreen(viewModel: VehicleViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = dimensionResource(CAR_UIR.dimen.dimension_50))
    ) {
        Row(modifier = Modifier.weight(AIR_CONDITION_VIEW_WEIGHT)) {
            Column(modifier = Modifier.weight(AIR_CONDITION_CONTROL_VIEW_WEIGHT)) {
                AirConditionControlView()
            }
            Column(modifier = Modifier.weight(AIR_CONDITION_PANEL_VIEW_WEIGHT)) {
                AirConditionPanelView()
            }
            Column(modifier = Modifier.weight(FRAGRANCE_VIEW_WEIGHT)) {
                FragranceView()
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(CAR_UIR.dimen.dimension_80)))
        Row(modifier = Modifier.weight(SEAT_AND_LIGHT_VIEW_WEIGHT)) {
            Column(modifier = Modifier.weight(SEAT_VIEW_WEIGHT)) {
                SeatView(
                    seatUiState = MutableStateFlow(SeatUiState()),
                    toggleMassage = {},
                    toggleSeatCooling = {},
                    toggleSeatHeating = {},
                    toggleSeatAngle = {},
                    toggleSeatMemory1 = {},
                    toggleSeatMemory2 = {},
                    toggleSeatMemory3 = {},
                    toggleSeatMemoryPlus = {})
            }
            Column(modifier = Modifier.weight(ATMOSPHERE_LIGHT_VIEW_WEIGHT)) {
                AtmosphereLightView()
            }
        }
    }
}

private const val AIR_CONDITION_VIEW_WEIGHT = 2f
private const val SEAT_AND_LIGHT_VIEW_WEIGHT = 1f

private const val AIR_CONDITION_CONTROL_VIEW_WEIGHT = 1f
private const val AIR_CONDITION_PANEL_VIEW_WEIGHT = 5f
private const val FRAGRANCE_VIEW_WEIGHT = 3f

private const val SEAT_VIEW_WEIGHT = 6F
private const val ATMOSPHERE_LIGHT_VIEW_WEIGHT = 2F