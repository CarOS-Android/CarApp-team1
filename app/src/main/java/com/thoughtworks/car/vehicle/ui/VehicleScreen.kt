package com.thoughtworks.car.vehicle.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.ui.theme.Theme
import com.thoughtworks.car.vehicle.VehicleViewModel
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionControlView
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionPanelView
import com.thoughtworks.car.vehicle.ui.aircondition.FragranceView
import com.thoughtworks.car.vehicle.ui.atmospherelight.AtmosphereLightView
import com.thoughtworks.car.vehicle.ui.seat.SeatMemoryPresetSlots
import com.thoughtworks.car.vehicle.ui.seat.SeatView

@Composable
fun VehicleScreen(viewModel: VehicleViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = 36.dp, vertical = 60.dp)
    ) {
        Row(modifier = Modifier.weight(AIR_CONDITION_VIEW_WEIGHT)) {
            Column(modifier = Modifier.weight(AIR_CONDITION_CONTROL_VIEW_WEIGHT)) {
                AirConditionControlView(
                    airConditionControlUiState = viewModel.airConditionControlUseCase.uiState,
                    toggleHvacPowerFeature = { viewModel.airConditionControlUseCase.toggleHvacPower() },
                    toggleHvacAcFeature = { viewModel.airConditionControlUseCase.toggleHvacAc() },
                    toggleHvacAutoFeature = { viewModel.airConditionControlUseCase.toggleHvacAuto() },
                    toggleFragranceFeature = { viewModel.airConditionControlUseCase.toggleFragranceSwitch() }
                )
            }
            Spacer(modifier = Modifier.width(70.dp))
            Column(modifier = Modifier.weight(AIR_CONDITION_PANEL_VIEW_WEIGHT)) {
                AirConditionPanelView()
            }
            Column(modifier = Modifier.weight(FRAGRANCE_VIEW_WEIGHT)) {
                FragranceView()
            }
        }
        Row(modifier = Modifier.weight(SEAT_AND_LIGHT_VIEW_WEIGHT)) {
            Column(modifier = Modifier.weight(SEAT_VIEW_WEIGHT)) {
                Row {
                    SeatView(
                        seatUiState = viewModel.seatRow1LeftUseCase.uiState,
                        toggleMassage = {},
                        toggleSeatCooling = { viewModel.seatRow1LeftUseCase.toggleSeatCooling() },
                        toggleSeatHeating = { viewModel.seatRow1LeftUseCase.toggleSeatHeating() },
                        toggleSeatTilt = { viewModel.seatRow1LeftUseCase.toggleSeatTilt() },
                        toggleSeatMemory1 = {
                            viewModel.seatRow1LeftUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.FIRST_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemory2 = {
                            viewModel.seatRow1LeftUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.SECOND_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemory3 = {
                            viewModel.seatRow1LeftUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.THIRD_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemoryPlus = {
                            viewModel.seatRow1LeftUseCase.toggleSeatMemoryPlus()
                        }
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    SeatView(
                        seatUiState = viewModel.seatRow1RightUseCase.uiState,
                        toggleMassage = {},
                        toggleSeatCooling = { viewModel.seatRow1RightUseCase.toggleSeatCooling() },
                        toggleSeatHeating = { viewModel.seatRow1RightUseCase.toggleSeatHeating() },
                        toggleSeatTilt = { viewModel.seatRow1RightUseCase.toggleSeatTilt() },
                        toggleSeatMemory1 = {
                            viewModel.seatRow1RightUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.FIRST_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemory2 = {
                            viewModel.seatRow1RightUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.SECOND_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemory3 = {
                            viewModel.seatRow1RightUseCase.toggleSeatMemoryPresetSlot(
                                SeatMemoryPresetSlots.THIRD_SLOT.ordinal + 1
                            )
                        },
                        toggleSeatMemoryPlus = {
                            viewModel.seatRow1RightUseCase.toggleSeatMemoryPlus()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            Column(modifier = Modifier.weight(ATMOSPHERE_LIGHT_VIEW_WEIGHT)) {
                AtmosphereLightView()
            }
        }
    }
}

private const val AIR_CONDITION_VIEW_WEIGHT = 9f
private const val SEAT_AND_LIGHT_VIEW_WEIGHT = 5f

private const val AIR_CONDITION_CONTROL_VIEW_WEIGHT = 1f
private const val AIR_CONDITION_PANEL_VIEW_WEIGHT = 5f
private const val FRAGRANCE_VIEW_WEIGHT = 3f

private const val SEAT_VIEW_WEIGHT = 22F
private const val ATMOSPHERE_LIGHT_VIEW_WEIGHT = 6F
