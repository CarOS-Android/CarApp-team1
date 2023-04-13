package com.thoughtworks.car.dashboard.ui.light

import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
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

data class LightUiState(
    val hazardLightState: Boolean = false,
    val headLightState: Boolean = false,
    val highBeamLightState: Boolean = false,
)

class LightUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {
    private val _hazardLightState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _headLightState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _highBeamLightState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<LightUiState> = combine(
        _hazardLightState, _headLightState, _highBeamLightState
    ) { hazardLightState, headLightState, highBeamLightState ->
        LightUiState(hazardLightState, headLightState, highBeamLightState)
    }.stateIn(coroutineScope, WhileUiSubscribed, LightUiState())

    private var hazardLightListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car property value changed $value")
            val hazardLight = value.value as Int
            _hazardLightState.value = hazardLight > 0
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            hazardLightListener,
            VehiclePropertyIds.HAZARD_LIGHTS_SWITCH,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleHazardLight() {
        carPropertyManager.setIntProperty(
            VehiclePropertyIds.HAZARD_LIGHTS_SWITCH,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL,
            if (_hazardLightState.value) 0 else 1
        )
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(hazardLightListener)
    }
}