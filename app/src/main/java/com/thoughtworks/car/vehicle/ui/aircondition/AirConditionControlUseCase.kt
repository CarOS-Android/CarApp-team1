package com.thoughtworks.car.vehicle.ui.aircondition

import android.car.VehicleAreaSeat
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

data class AirConditionControlUiState(
    val powerState: Boolean = false,
    val acState: Boolean = false,
    val autoState: Boolean = false,
    val fragranceState: Boolean = false
)

class AirConditionControlUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) : BaseUseCase {
    private val _powerState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _acState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _autoState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<AirConditionControlUiState> = combine(
        _powerState, _acState, _autoState
    ) { powerState, acState, autoState ->
        AirConditionControlUiState(powerState, acState, autoState)
    }.stateIn(coroutineScope, WhileUiSubscribed, AirConditionControlUiState())

    private var hvacPowerListener = carPropertyEventCallback(_powerState)
    private var hvacAcListener = carPropertyEventCallback(_acState)
    private var hvacAutoListener = carPropertyEventCallback(_autoState)

    init {
        carPropertyManager.registerCallback(
            hvacPowerListener,
            VehiclePropertyIds.HVAC_POWER_ON,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            hvacAcListener,
            VehiclePropertyIds.HVAC_AC_ON,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            hvacAutoListener,
            VehiclePropertyIds.HVAC_AUTO_ON,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    private fun carPropertyEventCallback(hvacUiState: MutableStateFlow<Boolean>) =
        object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(value: CarPropertyValue<Any>) {
                Logger.i("Car property value changed $value")
                val hvac = value.value as Boolean
                hvacUiState.value = hvac
            }

            override fun onErrorEvent(propId: Int, zone: Int) {
                Logger.w("Received error car property event, propId=$propId")
            }
        }

    fun toggleHvacPower() {
        setHvacState(VehiclePropertyIds.HVAC_POWER_ON, _powerState)
    }

    fun toggleHvacAc() {
        setHvacState(VehiclePropertyIds.HVAC_AC_ON, _acState)
    }

    fun toggleHvacAuto() {
        setHvacState(VehiclePropertyIds.HVAC_AUTO_ON, _autoState)
    }

    private fun setHvacState(propertyIds: Int, hvacUiState: MutableStateFlow<Boolean>) {
        carPropertyManager.setBooleanProperty(
            propertyIds,
            ALL,
            hvacUiState.value.not()
        )
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(hvacPowerListener)
        carPropertyManager.unregisterCallback(hvacAcListener)
        carPropertyManager.unregisterCallback(hvacAutoListener)
    }

    companion object {
        private const val LEFT =
            VehicleAreaSeat.SEAT_ROW_1_LEFT or VehicleAreaSeat.SEAT_ROW_2_LEFT or VehicleAreaSeat.SEAT_ROW_2_CENTER

        private const val RIGHT =
            VehicleAreaSeat.SEAT_ROW_1_RIGHT or VehicleAreaSeat.SEAT_ROW_2_RIGHT

        private const val ALL = LEFT or RIGHT
    }
}