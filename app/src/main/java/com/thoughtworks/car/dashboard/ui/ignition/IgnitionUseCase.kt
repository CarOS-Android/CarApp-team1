package com.thoughtworks.car.dashboard.ui.ignition

import android.car.VehicleIgnitionState.UNDEFINED
import android.car.VehiclePropertyIds.IGNITION_STATE
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class IgnitionUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {
    private val _ignitionState: MutableStateFlow<Int> = MutableStateFlow(UNDEFINED)
    val uiState: StateFlow<Int> = _ignitionState

    private var ignitionListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car ignition state property value changed $value")
            _ignitionState.value = value.value as Int
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            ignitionListener,
            IGNITION_STATE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(ignitionListener)
    }
}
