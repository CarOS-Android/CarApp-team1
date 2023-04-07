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

data class ParkingUiState(
    val parkingBrakeUiState: Boolean = true,
)

class ParkingBrakeUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {
    private val _autoParkingBrakeState: MutableStateFlow<ParkingUiState> =
        MutableStateFlow(ParkingUiState())
    val uiState: StateFlow<ParkingUiState> = _autoParkingBrakeState

    private var parkingBrakeListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            val parking = value.value as Boolean
            Logger.i("Car parking brake property value changed $value")
            _autoParkingBrakeState.value = ParkingUiState(parking)
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            parkingBrakeListener,
            VehiclePropertyIds.PARKING_BRAKE_ON,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleParkingBrake() {
        val on = carPropertyManager.getBooleanProperty(
            VehiclePropertyIds.PARKING_BRAKE_ON,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL
        )
        Logger.i("Car parking brake property value is $on")
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(parkingBrakeListener)
    }
}
