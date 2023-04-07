package com.thoughtworks.car.dashboard.ui.hvac

import android.car.VehicleAreaSeat
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.lifecycle.ViewModel
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

const val DEFAULT_TEMPERATURE = 18F

data class HvacUiState(
    val leftTemperature: Float = DEFAULT_TEMPERATURE,
    val rightTemperature: Float = DEFAULT_TEMPERATURE,
    val hvacFanSpeed: Int = 0
)

@HiltViewModel
class HvacViewModel @Inject constructor(
    @ApplicationScope val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) : ViewModel() {

    private val _leftTemperature: MutableStateFlow<Float> = MutableStateFlow(DEFAULT_TEMPERATURE)
    private val _rightTemperature: MutableStateFlow<Float> = MutableStateFlow(DEFAULT_TEMPERATURE)
    private val _fanSpeed: MutableStateFlow<Int> = MutableStateFlow(0)

    val uiState: StateFlow<HvacUiState> = combine(
        _leftTemperature, _rightTemperature, _fanSpeed
    ) { leftTemperature, rightTemperature, fanSpeed ->
        HvacUiState(leftTemperature, rightTemperature, fanSpeed)
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
    private var hvacFanSpeedListener = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car HVAC fan speed value changed $value")
            when (value.propertyId) {
                VehiclePropertyIds.HVAC_FAN_SPEED -> _fanSpeed.value = value.value as Int
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
        carPropertyManager.registerCallback(
            hvacFanSpeedListener,
            VehiclePropertyIds.HVAC_FAN_SPEED,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun setHvacLeftTemperature(temperature: Float) {
        try {
            carPropertyManager.setFloatProperty(
                VehiclePropertyIds.HVAC_TEMPERATURE_SET, HVAC_AREA_LEFT, temperature
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set HVAC left temperature fail", e)
        } catch (e: SecurityException) {
            Logger.e("set HVAC left temperature fail", e)
        }
    }

    fun setHvacRightTemperature(temperature: Float) {
        try {
            carPropertyManager.setFloatProperty(
                VehiclePropertyIds.HVAC_TEMPERATURE_SET, HVAC_AREA_RIGHT, temperature
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set HVAC right temperature fail", e)
        } catch (e: SecurityException) {
            Logger.e("set HVAC right temperature fail", e)
        }
    }

    fun setHvacFanSpeed(hvacFanSpeed: Int) {
        try {
            carPropertyManager.setIntProperty(
                VehiclePropertyIds.HVAC_FAN_SPEED,
                HVAC_FAN_SPEED_AREA,
                hvacFanSpeed
            )
        } catch (e: IllegalArgumentException) {
            Logger.e("set HVAC fan speed fail", e)
        }
    }

    companion object {
        private const val HVAC_AREA_LEFT =
            VehicleAreaSeat.SEAT_ROW_1_LEFT or VehicleAreaSeat.SEAT_ROW_2_LEFT or VehicleAreaSeat.SEAT_ROW_2_CENTER
        private const val HVAC_AREA_RIGHT =
            VehicleAreaSeat.SEAT_ROW_1_RIGHT or VehicleAreaSeat.SEAT_ROW_2_RIGHT
        private const val HVAC_FAN_SPEED_AREA = VehicleAreaSeat.SEAT_ROW_1_LEFT or
            VehicleAreaSeat.SEAT_ROW_1_RIGHT or VehicleAreaSeat.SEAT_ROW_2_LEFT or
            VehicleAreaSeat.SEAT_ROW_2_CENTER or VehicleAreaSeat.SEAT_ROW_2_RIGHT
    }
}
