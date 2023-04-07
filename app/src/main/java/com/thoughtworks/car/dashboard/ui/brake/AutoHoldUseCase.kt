package com.thoughtworks.car.dashboard.ui.brake

import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class AutoHoldUiState(
    val autoHoldState: Boolean = true,
)

class AutoHoldUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {
    private val _uiState: MutableStateFlow<AutoHoldUiState> = MutableStateFlow(AutoHoldUiState())
    val uiState: StateFlow<AutoHoldUiState> = _uiState

    private var autoHoldListener = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car auto hold property value changed $value")
            val autoHoldState = value.value as Boolean
            _uiState.value = AutoHoldUiState(autoHoldState)
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            autoHoldListener,
            VehiclePropertyIds.PARKING_BRAKE_AUTO_APPLY,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleAutoHold() {
        val on = carPropertyManager.getBooleanProperty(
            VehiclePropertyIds.PARKING_BRAKE_AUTO_APPLY,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL
        )
        Logger.i("Car auto hold property value is $on")
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(autoHoldListener)
    }
}
