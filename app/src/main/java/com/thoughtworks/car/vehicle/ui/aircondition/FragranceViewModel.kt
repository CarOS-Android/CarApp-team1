package com.thoughtworks.car.vehicle.ui.aircondition

import android.car.VehicleAreaSeat
import android.car.VehiclePropertyIds
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import androidx.annotation.DrawableRes
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

data class FragranceUiState(
    val mainDrivingSeat: FRAGRANCE = FRAGRANCE.STAR,
    val copilotDrivingSeat: FRAGRANCE = FRAGRANCE.SECRET,
    val rearSeat: FRAGRANCE = FRAGRANCE.SUNSHINE
)

const val STAR_MODE = 1
const val SUNSHINE_MODE = 2
const val SECRET_MODE = 3

enum class FRAGRANCE(val value: Int, @DrawableRes val res: Int) {
    STAR(STAR_MODE, R.drawable.res_item_xingchen),
    SUNSHINE(SUNSHINE_MODE, R.drawable.res_item_yangguang),
    SECRET(SECRET_MODE, R.drawable.res_item_mijing),
}

@HiltViewModel
class FragranceViewModel @Inject constructor(
    @ApplicationScope val coroutineScope: CoroutineScope,
    val carPropertyManager: CarPropertyManager
) : ViewModel() {
    private val _mainDrivingSeat = MutableStateFlow(FRAGRANCE.STAR)
    private val _copilotDrivingSeat = MutableStateFlow(FRAGRANCE.STAR)
    private val _rearSeat = MutableStateFlow(FRAGRANCE.STAR)

    val uiState: StateFlow<FragranceUiState> = combine(
        _mainDrivingSeat, _copilotDrivingSeat, _rearSeat
    ) { _mainDrivingSeat, _copilotDrivingSeat, _rearSeat ->
        FragranceUiState(_mainDrivingSeat, _copilotDrivingSeat, _rearSeat)
    }.stateIn(coroutineScope, WhileUiSubscribed, FragranceUiState())

    private var fragranceModeCallback = object : CarPropertyManager.CarPropertyEventCallback {

        override fun onChangeEvent(value: CarPropertyValue<Any>) {
            Logger.i("Car fragrance property value changed $value")
            val mode = value.value as Int
            val fragrance = FRAGRANCE.values().find { it.value == mode } ?: FRAGRANCE.STAR
            when (value.areaId) {
                VehicleAreaSeat.SEAT_ROW_1_LEFT -> _mainDrivingSeat.value = fragrance
                VehicleAreaSeat.SEAT_ROW_1_RIGHT -> _copilotDrivingSeat.value = fragrance
                VehicleAreaSeat.SEAT_ROW_2_CENTER -> _rearSeat.value = fragrance
            }
        }

        override fun onErrorEvent(propId: Int, zone: Int) {
            Logger.w("Received error car property event, propId=$propId")
        }
    }

    init {
        carPropertyManager.registerCallback(
            fragranceModeCallback,
            VehiclePropertyIds.FRAGRANCE_LEVEL,
            CarPropertyManager.SENSOR_RATE_ONCHANGE
        )
    }

    fun changeMode(value: Int, seat: Int) {
        carPropertyManager.setIntProperty(VehiclePropertyIds.FRAGRANCE_LEVEL, seat, value)
    }

    override fun onCleared() {
        carPropertyManager.unregisterCallback(fragranceModeCallback)
        super.onCleared()
    }
}
