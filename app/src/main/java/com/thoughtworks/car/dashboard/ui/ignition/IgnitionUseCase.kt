package com.thoughtworks.car.dashboard.ui.ignition

import android.car.VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL
import android.car.VehicleIgnitionState.UNDEFINED
import android.car.VehiclePropertyIds.IGNITION_STATE
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.utils.WhileUiSubscribed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class IgnitionUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    carPropertyManager: CarPropertyManager
) {
    private val _ignitionState: MutableStateFlow<Int> =
        MutableStateFlow(UNDEFINED)
    val uiState: StateFlow<Int> =
        _ignitionState.stateIn(
            coroutineScope,
            WhileUiSubscribed,
            carPropertyManager.getIntProperty(
                IGNITION_STATE,
                VEHICLE_AREA_TYPE_GLOBAL
            )
        )

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
        Logger.i(
            "Car ignition default state property is: ${
                carPropertyManager.getIntProperty(
                    IGNITION_STATE, VEHICLE_AREA_TYPE_GLOBAL
                )
            }"
        )
        carPropertyManager.registerCallback(
            ignitionListener,
            IGNITION_STATE,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

}