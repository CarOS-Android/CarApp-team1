package com.thoughtworks.car.vehicle.ui.aircondition

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.thoughtworks.car.R
import com.thoughtworks.car.core.di.ApplicationScope
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
    val rearSeat: FRAGRANCE = FRAGRANCE.SUN_LIGHT
)

enum class FRAGRANCE(@DrawableRes val res: Int) {
    STAR(R.drawable.res_item_xingchen),
    SUN_LIGHT(R.drawable.res_item_yangguang),
    SECRET(R.drawable.res_item_mijing)
}

@HiltViewModel
class FragranceViewModel @Inject constructor(@ApplicationScope val coroutineScope: CoroutineScope) :
    ViewModel() {
    private val _mainDrivingSeat: MutableStateFlow<FRAGRANCE> = MutableStateFlow(FRAGRANCE.STAR)
    private val _copilotDrivingSeat: MutableStateFlow<FRAGRANCE> =
        MutableStateFlow(FRAGRANCE.SECRET)
    private val _rearSeat: MutableStateFlow<FRAGRANCE> = MutableStateFlow(FRAGRANCE.SUN_LIGHT)

    val uiState: StateFlow<FragranceUiState> = combine(
        _mainDrivingSeat, _copilotDrivingSeat, _rearSeat
    ) { _mainDrivingSeat, _copilotDrivingSeat, _rearSeat ->
        FragranceUiState(_mainDrivingSeat, _copilotDrivingSeat, _rearSeat)
    }.stateIn(coroutineScope, WhileUiSubscribed, FragranceUiState())
}