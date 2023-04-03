package com.thoughtworks.car.dashboard.ui.door

import androidx.annotation.DrawableRes
import com.thoughtworks.car.R
import com.thoughtworks.car.core.di.ApplicationScope
import com.thoughtworks.car.core.utils.WhileUiSubscribed
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DoorUiState(
    val doorState: Boolean = false,
    val doorHoodState: Boolean = false,
    val doorRearState: Boolean = false,
    @DrawableRes val car: Int = R.drawable.ic_car
)

class DoorUseCase @Inject constructor(@ApplicationScope private val coroutineScope: CoroutineScope) {

    private val _doorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _doorHoodState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _doorRearState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<DoorUiState> = combine(
        _doorState,
        _doorHoodState,
        _doorRearState
    ) { doorState, doorHoodState, doorRearState ->
        DoorUiState(doorState, doorHoodState, doorRearState)
    }.stateIn(coroutineScope, WhileUiSubscribed, DoorUiState())
}
