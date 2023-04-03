package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.door.DoorUiState
import com.thoughtworks.car.dashboard.ui.door.DoorUseCase
import com.thoughtworks.car.dashboard.ui.navi.NaviUiState
import com.thoughtworks.car.dashboard.ui.navi.NaviUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DashboardUiState(
    val doorUiState: StateFlow<DoorUiState>,
    val naviUiState: StateFlow<NaviUiState>,
    // add more view state here
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    doorUseCase: DoorUseCase,
    naviUseCase: NaviUseCase,
    // add more useCase here
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> =
        MutableStateFlow(DashboardUiState(doorUseCase.uiState, naviUseCase.uiState))
}
