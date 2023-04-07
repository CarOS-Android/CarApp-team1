package com.thoughtworks.car.dashboard.ui.navi

import androidx.annotation.DrawableRes
import com.thoughtworks.car.R
import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class NaviUiState(
    @DrawableRes val image: Int = R.drawable.bg_fake_dashboard_navi,
)

class NaviUseCase @Inject constructor() : BaseUseCase {
    val uiState: StateFlow<NaviUiState> = MutableStateFlow(NaviUiState())

    override fun onCleared() {
        // do nothing
    }
}
