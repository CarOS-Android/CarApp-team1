package com.thoughtworks.car.dashboard.ui.hvac

import android.car.VehicleAreaMirror
import android.car.VehicleAreaSeat
import android.car.VehicleAreaType
import android.car.VehicleAreaWindow
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.lifecycle.ViewModel
import com.thoughtworks.car.R
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.utils.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HvacCheckBoxUiState(
    val isFrontWindowDefrosterOn: Boolean = false,
    val isRearWindowDefrosterOn: Boolean = false,
    val isSideMirrorHeatedOn: Boolean = false,
    val isRecirculationOn: Boolean = false,
    val isFragranceOn: Boolean = false
)

enum class HvacOption(val resId: Int) {
    REAR_WINDOW_DEFROSTER(R.drawable.res_background_window),
    FRAGRANCE(R.drawable.res_fragrance),
    RECIRCULATION_MODE_ON(R.drawable.res_inner_loop),
    RECIRCULATION_MODE_OFF(R.drawable.res_out_loop),
    SIDE_MIRROR_HEAT(R.drawable.res_rearview_window),
    FRONT_WINDOW_DEFROSTER(R.drawable.res_foreground_window)
}

@HiltViewModel
class HvacCheckBoxViewModel @Inject constructor(
    @ApplicationScope val coroutineScope: CoroutineScope,
    private val carPropertyManager: CarPropertyManager
) : ViewModel() {
    private val _isFrontWindowDefrosterOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isRearWindowDefrosterOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var _isSideMirrorHeatOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var _isRecirculationModeModeOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var _isFragranceOn: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<HvacCheckBoxUiState> = combine(
        _isFrontWindowDefrosterOn,
        _isRearWindowDefrosterOn,
        _isSideMirrorHeatOn,
        _isRecirculationModeModeOn,
        _isFragranceOn
    ) { _isForegroundWindowDefrosterOn, _isRearWindowDefrosterOn,
        _isSideMirrorHeatOn, _isRecirculationModeModeOn, _isFragranceOn ->
        HvacCheckBoxUiState(
            _isForegroundWindowDefrosterOn,
            _isRearWindowDefrosterOn,
            _isSideMirrorHeatOn,
            _isRecirculationModeModeOn,
            _isFragranceOn
        )
    }.stateIn(coroutineScope, WhileUiSubscribed, HvacCheckBoxUiState())

    private var hvacPropertyListener = object : CarPropertyManager.CarPropertyEventCallback {
        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car HVAC property value changed $value")
            when (value.propertyId) {
                VehiclePropertyIds.HVAC_DEFROSTER -> {
                    if (value.areaId == VehicleAreaWindow.WINDOW_FRONT_WINDSHIELD) {
                        _isFrontWindowDefrosterOn.value = value.value as Boolean
                    } else {
                        _isRearWindowDefrosterOn.value = value.value as Boolean
                    }
                }
                VehiclePropertyIds.HVAC_SIDE_MIRROR_HEAT -> {
                    _isSideMirrorHeatOn.value = value.value as Int > 0
                }
                VehiclePropertyIds.HVAC_RECIRC_ON -> {
                    _isRecirculationModeModeOn.value = value.value as Boolean
                }
                VehiclePropertyIds.FRAGRANCE_SWITCH -> {
                    _isFragranceOn.value = value.value as Boolean
                }
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        val properties = listOf(
            VehiclePropertyIds.HVAC_DEFROSTER,
            VehiclePropertyIds.HVAC_SIDE_MIRROR_HEAT,
            VehiclePropertyIds.HVAC_RECIRC_ON,
            VehiclePropertyIds.FRAGRANCE_SWITCH
        )

        properties.forEach { property ->
            carPropertyManager.registerCallback(
                hvacPropertyListener, property, CarPropertyManager.SENSOR_RATE_ONCHANGE
            )
        }
    }

    fun createCheckedSet(uiState: HvacCheckBoxUiState): MutableSet<Int> {
        return mutableListOf<Int>().apply {
            if (uiState.isRearWindowDefrosterOn) add(HvacOption.REAR_WINDOW_DEFROSTER.ordinal)
            add(
                if (uiState.isRecirculationOn) {
                    HvacOption.RECIRCULATION_MODE_ON.ordinal
                } else {
                    HvacOption.RECIRCULATION_MODE_OFF.ordinal
                }
            )
            if (uiState.isFragranceOn) add(HvacOption.FRAGRANCE.ordinal)
            if (uiState.isSideMirrorHeatedOn) add(HvacOption.SIDE_MIRROR_HEAT.ordinal)
            if (uiState.isFrontWindowDefrosterOn) add(HvacOption.FRONT_WINDOW_DEFROSTER.ordinal)
        }.toMutableSet()
    }

    fun toggleFrontWindowDefroster() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.HVAC_DEFROSTER,
            VehicleAreaWindow.WINDOW_FRONT_WINDSHIELD,
            _isFrontWindowDefrosterOn.value.not()
        )
    }

    fun toggleRearWindowDefroster() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.HVAC_DEFROSTER,
            VehicleAreaWindow.WINDOW_REAR_WINDSHIELD,
            _isRearWindowDefrosterOn.value.not()
        )
    }

    fun toggleSideMirrorHeat() {
        carPropertyManager.setIntProperty(
            VehiclePropertyIds.HVAC_SIDE_MIRROR_HEAT,
            VehicleAreaMirror.MIRROR_DRIVER_LEFT or VehicleAreaMirror.MIRROR_DRIVER_RIGHT,
            if (_isSideMirrorHeatOn.value) 0 else 1
        )
    }

    fun toggleFragrance() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.FRAGRANCE_SWITCH,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL,
            _isFragranceOn.value.not()
        )
    }

    fun switchRecirculationModeMode() {
        carPropertyManager.setBooleanProperty(
            VehiclePropertyIds.HVAC_RECIRC_ON,
            VehicleAreaSeat.SEAT_ROW_1_LEFT
                or VehicleAreaSeat.SEAT_ROW_1_RIGHT
                or VehicleAreaSeat.SEAT_ROW_2_LEFT
                or VehicleAreaSeat.SEAT_ROW_2_CENTER
                or VehicleAreaSeat.SEAT_ROW_2_RIGHT,
            _isRecirculationModeModeOn.value.not()
        )
    }
}