package com.thoughtworks.car.vehicle

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionControlUseCase
import com.thoughtworks.car.vehicle.ui.aircondition.AirConditionPanelUseCase
import com.thoughtworks.car.vehicle.ui.aircondition.FragranceUseCase
import com.thoughtworks.car.vehicle.ui.atmospherelight.AtmosphereLightUseCase
import com.thoughtworks.car.vehicle.ui.seat.SeatRow1LeftUseCase
import com.thoughtworks.car.vehicle.ui.seat.SeatRow1RightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    val airConditionControlUseCase: AirConditionControlUseCase,
    val airConditionPanelUseCase: AirConditionPanelUseCase,
    val fragranceUseCase: FragranceUseCase,
    val seatRow1LeftUseCase: SeatRow1LeftUseCase,
    val seatRow1RightUseCase: SeatRow1RightUseCase,
    val atmosphereLightUseCase: AtmosphereLightUseCase
) : ViewModel() {
    override fun onCleared() {
        airConditionControlUseCase.onCleared()
        airConditionPanelUseCase.onCleared()
        fragranceUseCase.onCleared()
        seatRow1LeftUseCase.onCleared()
        seatRow1RightUseCase.onCleared()
        atmosphereLightUseCase.onCleared()
        super.onCleared()
    }
}