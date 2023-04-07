package com.thoughtworks.car.dashboard.ui.status

import android.car.VehicleAreaWindow
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.logging.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class StatusUiState(
    val windowLockState: Boolean = true,
)

class StatusUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager,
) {
    private val _uiState: MutableStateFlow<StatusUiState> = MutableStateFlow(StatusUiState())
    val uiState: StateFlow<StatusUiState> = _uiState

    private var windowLockListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car window lock property value changed $value")
            val windowLockState = value.value as Boolean
            _uiState.value = StatusUiState(windowLockState)
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            windowLockListener,
            VehiclePropertyIds.WINDOW_LOCK,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleWindowLock() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.WINDOW_LOCK,
            VehicleAreaWindow.WINDOW_ROW_1_RIGHT
                or VehicleAreaWindow.WINDOW_ROW_2_LEFT
                or VehicleAreaWindow.WINDOW_ROW_2_RIGHT,
            _uiState.value.windowLockState.not()
        )
    }
}
