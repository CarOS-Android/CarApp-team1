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

    private var hazardLightListener = carPropertyEventCallback(_hazardLightState)
    private var headLightListener = carPropertyEventCallback(_headLightState)
    private var highBeamLightListener = carPropertyEventCallback(_highBeamLightState)

    init {
        carPropertyManager.registerCallback(
            hazardLightListener,
            VehiclePropertyIds.HAZARD_LIGHTS_SWITCH,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            headLightListener,
            VehiclePropertyIds.HEADLIGHTS_SWITCH,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            highBeamLightListener,
            VehiclePropertyIds.HIGH_BEAM_LIGHTS_SWITCH,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    private fun carPropertyEventCallback(lightUiState: MutableStateFlow<Boolean>) =
        object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(value: CarPropertyValue<Any>) {
                Logger.i("Car property value changed $value")
                val light = value.value as Int
                lightUiState.value = light > 0

                if (_headLightState.value && _highBeamLightState.value) {
                    when (lightUiState) {
                        _headLightState -> setLightState(
                            VehiclePropertyIds.HIGH_BEAM_LIGHTS_SWITCH,
                            _highBeamLightState
                        )
                        _highBeamLightState -> setLightState(
                            VehiclePropertyIds.HEADLIGHTS_SWITCH,
                            _headLightState
                        )
                    }
                }
            }
            override fun onErrorEvent(propId: Int, zone: Int) {
                Logger.w("Received error car property event, propId=$propId")
            }
        }

    fun toggleHazardLight() {
        setLightState(VehiclePropertyIds.HAZARD_LIGHTS_SWITCH, _hazardLightState)
    }

    fun toggleHeadLight() {
        setLightState(VehiclePropertyIds.HEADLIGHTS_SWITCH, _headLightState)
    }

    fun toggleHighBeamLight() {
        setLightState(VehiclePropertyIds.HIGH_BEAM_LIGHTS_SWITCH, _highBeamLightState)
    }

    private fun setLightState(propertyIds: Int, lightUiState: MutableStateFlow<Boolean>) {
        carPropertyManager.setIntProperty(
            propertyIds,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL,
            if (lightUiState.value) 0 else 1
        )
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(hazardLightListener)
    }
}