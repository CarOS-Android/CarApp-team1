package com.thoughtworks.car.vehicle.ui.aircondition

import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class AirConditionControlUiState(
    val fragranceState: Boolean = false,
)

class AirConditionControlUseCase @Inject constructor() : BaseUseCase {
    private val _uiState: MutableStateFlow<AirConditionControlUiState> =
        MutableStateFlow(AirConditionControlUiState())
    val uiState: StateFlow<AirConditionControlUiState> = _uiState

    override fun onCleared() {
        // do nothing
    }
}