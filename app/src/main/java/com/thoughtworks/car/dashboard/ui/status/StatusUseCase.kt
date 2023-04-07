package com.thoughtworks.car.dashboard.ui.status

import android.car.VehicleAreaWindow
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.annotation.DrawableRes
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class StatusUiState(
    val windowLockState: Boolean = true,
)

data class StatusConfigration(
    @DrawableRes val iconOn: Int,
    @DrawableRes val iconOff: Int,
    val status: Boolean,
    val onClick: () -> Unit
)

class StatusUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager,
) : BaseUseCase {
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

    override fun onCleared() {
        carPropertyManager.unregisterCallback(windowLockListener)
    }
}
