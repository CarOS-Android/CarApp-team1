package com.thoughtworks.car.vehicle.ui.seat

import android.car.VehicleAreaSeat.SEAT_ROW_1_LEFT
import android.car.VehicleAreaSeat.SEAT_ROW_1_RIGHT
import android.car.VehicleAreaSeat.SEAT_UNKNOWN
import android.car.VehiclePropertyIds.HVAC_SEAT_TEMPERATURE
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.ui.BaseUseCase
import com.thoughtworks.car.core.utils.WhileUiSubscribed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class SeatUiState(
    val seatArea: Int = SEAT_UNKNOWN,
    val seatHeatingCoolingValue: Int = 0,
    val massageLevel: Int = 1,
    val seatMemory1State: Boolean = false,
    val seatMemory2State: Boolean = false,
    val seatMemory3State: Boolean = false,
    val seatMemoryPlusState: Boolean = false,
)

data class SeatHeatingCoolingState(
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    @ColorRes val colorOn: Int,
    @StringRes val text: Int,
    val value: Int,
    val onClick: () -> Unit
)

data class SeatMemoryState(
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    val status: Boolean,
    val onClick: () -> Unit
)

class SeatRow1LeftUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager,
) : BaseUseCase {
    private val _seatAreaState: MutableStateFlow<Int> = MutableStateFlow(SEAT_UNKNOWN)
    private val _seatHeatingCoolingState: MutableStateFlow<Int> = MutableStateFlow(0)
    val uiState: StateFlow<SeatUiState> = combine(
        _seatAreaState, _seatHeatingCoolingState
    ) { _, seatHeatingCoolingState ->
        SeatUiState(
            seatArea = SEAT_ROW_1_LEFT,
            seatHeatingCoolingValue = seatHeatingCoolingState
        )
    }.stateIn(
        coroutineScope,
        WhileUiSubscribed,
        SeatUiState(
            seatArea = SEAT_ROW_1_LEFT,
            seatHeatingCoolingValue = carPropertyManager.getIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_LEFT
            )
        )
    )

    private var seatHeatingCoolingListener =
        object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(value: CarPropertyValue<Any>) {
                Logger.i("Car seat heating cooling state property value changed $value")
                if (value.areaId == SEAT_ROW_1_LEFT) {
                    _seatHeatingCoolingState.value = value.value as Int
                }
            }

            override fun onErrorEvent(propId: Int, zone: Int) {
                Logger.w("Received error car property event, propId=$propId")
            }
        }

    init {
        carPropertyManager.registerCallback(
            seatHeatingCoolingListener,
            HVAC_SEAT_TEMPERATURE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleSeatHeating() {
        try {
            carPropertyManager.setIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_LEFT,
                _seatHeatingCoolingState.value + 1
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set Right Seat Heating fail", e)
        }
    }

    fun toggleSeatCooling() {
        try {
            carPropertyManager.setIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_LEFT,
                _seatHeatingCoolingState.value - 1
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set Right Seat Cooling fail", e)
        }
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(seatHeatingCoolingListener)
    }
}

class SeatRow1RightUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager,
) : BaseUseCase {
    private val _seatAreaState: MutableStateFlow<Int> = MutableStateFlow(SEAT_UNKNOWN)
    private val _seatHeatingCoolingState: MutableStateFlow<Int> = MutableStateFlow(0)
    val uiState: StateFlow<SeatUiState> = combine(
        _seatAreaState, _seatHeatingCoolingState
    ) { _, seatHeatingCoolingState ->
        SeatUiState(
            seatArea = SEAT_ROW_1_RIGHT,
            seatHeatingCoolingValue = seatHeatingCoolingState
        )
    }.stateIn(
        coroutineScope,
        WhileUiSubscribed,
        SeatUiState(
            seatArea = SEAT_ROW_1_RIGHT,
            seatHeatingCoolingValue = carPropertyManager.getIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_RIGHT
            )
        )
    )

    private var seatHeatingCoolingListener =
        object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(value: CarPropertyValue<Any>) {
                Logger.i("Car seat heating cooling state property value changed $value")
                if (value.areaId == SEAT_ROW_1_RIGHT) {
                    _seatHeatingCoolingState.value = value.value as Int
                }
            }

            override fun onErrorEvent(propId: Int, zone: Int) {
                Logger.w("Received error car property event, propId=$propId")
            }
        }

    init {
        carPropertyManager.registerCallback(
            seatHeatingCoolingListener,
            HVAC_SEAT_TEMPERATURE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleSeatHeating() {
        try {
            carPropertyManager.setIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_RIGHT,
                _seatHeatingCoolingState.value + 1
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set Right Seat Heating fail", e)
        }
    }

    fun toggleSeatCooling() {
        try {
            carPropertyManager.setIntProperty(
                HVAC_SEAT_TEMPERATURE,
                SEAT_ROW_1_RIGHT,
                _seatHeatingCoolingState.value - 1
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set Right Seat Cooling fail", e)
        }
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(seatHeatingCoolingListener)
    }
}