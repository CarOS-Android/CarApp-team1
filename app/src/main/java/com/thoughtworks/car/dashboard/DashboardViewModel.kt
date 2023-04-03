package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.navi.NaviUseCase
import com.thoughtworks.car.dashboard.ui.navi.NaviViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class DashboardUiState(
    val naviViewState: StateFlow<NaviViewState>,
    // add more view state here
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    naviUseCase: NaviUseCase,
    // add more delegates here
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> =
        MutableStateFlow(DashboardUiState(naviUseCase.uiState))
}
