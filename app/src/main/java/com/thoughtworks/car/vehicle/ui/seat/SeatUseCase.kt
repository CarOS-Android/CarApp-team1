package com.thoughtworks.car.vehicle.ui.seat

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thoughtworks.car.core.ui.BaseUseCase
import javax.inject.Inject

data class SeatUiState(
    @StringRes val seatArea: Int,
    val seatHeatingState: Boolean = true,
    val seatHeatingLevel: Int = 2,
    val seatCoolingState: Boolean = false,
    val seatCoolingLevel: Int = 0,
    val massageState: Boolean = true,
    val massageLevel: Int = 1,
    val seatMemory1State: Boolean = false,
    val seatMemory2State: Boolean = false,
    val seatMemory3State: Boolean = false,
    val seatMemoryPlusState: Boolean = false,
)

data class SeatFeatureState(
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    @ColorRes val colorOn: Int,
    @StringRes val text: Int,
    val status: Boolean,
    val level: Int,
    val onClick: () -> Unit
)

data class SeatMemoryState(
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    val status: Boolean,
    val onClick: () -> Unit
)

class SeatUseCase @Inject constructor() : BaseUseCase {
    override fun onCleared() {
        // do nothing
    }
}