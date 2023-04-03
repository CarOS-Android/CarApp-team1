package com.thoughtworks.car.dashboard.ui.door

import android.car.VehicleAreaDoor
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.annotation.DrawableRes
import com.thoughtworks.car.R
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.utils.WhileUiSubscribed
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DoorUiState(
    val doorSeatLock: Boolean = true,
    val doorHoodState: Boolean = false,
    val doorRearState: Boolean = false,
    @DrawableRes val car: Int = R.drawable.ic_car
)

class DoorUseCase @Inject constructor(
    @ApplicationScope private val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) {

    private val _doorRow1LeftLock: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _doorRow1RightLock: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _doorRow2LeftLock: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _doorRow2RightLock: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _doorSeatLock: StateFlow<Boolean> = combine(
        _doorRow1LeftLock,
        _doorRow1RightLock,
        _doorRow2LeftLock,
        _doorRow2RightLock
    ) { doorRow1Left, doorRow1Right, doorRow2Left, doorRow2Right ->
        doorRow1Left and doorRow1Right and doorRow2Left and doorRow2Right
    }.stateIn(coroutineScope, WhileUiSubscribed, true)

    private val _doorHoodState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _doorRearState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<DoorUiState> = combine(
        _doorSeatLock, _doorHoodState, _doorRearState
    ) { doorSeatLock, doorHoodState, doorRearState ->
        DoorUiState(doorSeatLock, doorHoodState, doorRearState)
    }.stateIn(coroutineScope, WhileUiSubscribed, DoorUiState())

    private var doorLockListener = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car property value changed $value")
            val lock = value.value as Boolean
            when (value.areaId) {
                VehicleAreaDoor.DOOR_ROW_1_LEFT -> _doorRow1LeftLock.value = lock
                VehicleAreaDoor.DOOR_ROW_1_RIGHT -> _doorRow1RightLock.value = lock
                VehicleAreaDoor.DOOR_ROW_2_LEFT -> _doorRow2LeftLock.value = lock
                VehicleAreaDoor.DOOR_ROW_2_RIGHT -> _doorRow2RightLock.value = lock
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    private var doorPositionListener = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car property value changed $value")
            val position = value.value as Int
            when (value.areaId) {
                VehicleAreaDoor.DOOR_HOOD -> _doorHoodState.value = position > 0
                VehicleAreaDoor.DOOR_REAR -> _doorRearState.value = position > 0
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            doorLockListener, VehiclePropertyIds.DOOR_LOCK, CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
        carPropertyManager.registerCallback(
            doorPositionListener,
            VehiclePropertyIds.DOOR_POS,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun toggleSeatDoors() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.DOOR_LOCK,
            VehicleAreaDoor.DOOR_ROW_1_LEFT,
            _doorSeatLock.value.not()
        )
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.DOOR_LOCK,
            VehicleAreaDoor.DOOR_ROW_1_RIGHT,
            _doorSeatLock.value.not()
        )
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.DOOR_LOCK,
            VehicleAreaDoor.DOOR_ROW_2_LEFT,
            _doorSeatLock.value.not()
        )
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.DOOR_LOCK,
            VehicleAreaDoor.DOOR_ROW_2_RIGHT,
            _doorSeatLock.value.not()
        )
    }

    fun toggleHoodDoor() {
        // java.lang.IllegalArgumentException: Failed to set value for: 0x16400b00,
        // areaId: 0x10000000, error: android.os.ServiceSpecificException
//        carPropertyManager.setIntProperty(
//            VehiclePropertyIds.DOOR_POS,
//            VehicleAreaDoor.DOOR_HOOD,
//            if (_doorHoodState.value) 0 else 1
//        )
    }

    fun toggleRearDoor() {
        carPropertyManager.setIntProperty(
            VehiclePropertyIds.DOOR_POS,
            VehicleAreaDoor.DOOR_REAR,
            if (_doorRearState.value) 0 else 1
        )
    }
}
