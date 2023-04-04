package com.thoughtworks.car.dashboard.ui.hvac

import android.car.VehicleAreaSeat
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HvacUiState(
    val leftTemperature: String = "",
    val rightTemperature: String = "",
)

class HvacUseCase @Inject constructor(
    @ApplicationScope val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) {

    private val _leftTemperature: MutableStateFlow<Float?> = MutableStateFlow(null)
    private val _rightTemperature: MutableStateFlow<Float?> = MutableStateFlow(null)

    val uiState: StateFlow<HvacUiState> = combine(
        _leftTemperature,
        _rightTemperature
    ) { leftTemperature, rightTemperature ->
        HvacUiState("$leftTemperature\u00b0", "$rightTemperature\u00b0")
    }.stateIn(coroutineScope, WhileSubscribed(), HvacUiState())

    private var hvacTemperatureListener = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car HVAC property value changed $value")
            when (value.areaId) {
                HVAC_AREA_LEFT -> _leftTemperature.value = value.value as Float
                HVAC_AREA_RIGHT -> _rightTemperature.value = value.value as Float
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            hvacTemperatureListener,
            VehiclePropertyIds.HVAC_TEMPERATURE_SET,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun setHvacLeftTemperature(temperature: Float) {
        try {
            carPropertyManager.setFloatProperty(
                VehiclePropertyIds.HVAC_TEMPERATURE_SET,
                HVAC_AREA_LEFT,
                temperature
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set HVAC left temperature fail", e)
        }
    }

    fun setHvacRightTemperature(temperature: Float) {
        try {
            carPropertyManager.setFloatProperty(
                VehiclePropertyIds.HVAC_TEMPERATURE_SET,
                HVAC_AREA_RIGHT,
                temperature
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set HVAC right temperature fail", e)
        }
    }

    companion object {
        private const val HVAC_AREA_LEFT = VehicleAreaSeat.SEAT_ROW_1_LEFT or
            VehicleAreaSeat.SEAT_ROW_2_LEFT or VehicleAreaSeat.SEAT_ROW_2_CENTER
        private const val HVAC_AREA_RIGHT = VehicleAreaSeat.SEAT_ROW_1_RIGHT or
            VehicleAreaSeat.SEAT_ROW_2_RIGHT
    }
}
