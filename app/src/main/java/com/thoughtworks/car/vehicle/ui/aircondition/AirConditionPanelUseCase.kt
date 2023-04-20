package com.thoughtworks.car.vehicle.ui.aircondition

import android.car.VehicleAreaType
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AirConditionPanelUseCase @Inject constructor(
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {

    private val _fragranceSwitch = MutableStateFlow(false)

    private val fragranceSwitchCallback = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car fragrance switch property value changed $value")
            _fragranceSwitch.value = value.value as Boolean
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            fragranceSwitchCallback,
            VehiclePropertyIds.FRAGRANCE_SWITCH,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleFragranceSwitch() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.FRAGRANCE_SWITCH,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL,
            _fragranceSwitch.value.not()
        )
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(fragranceSwitchCallback)
    }
}
