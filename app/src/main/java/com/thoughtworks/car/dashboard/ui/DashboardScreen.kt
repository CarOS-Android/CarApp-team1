package com.thoughtworks.car.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.dashboard.DashboardViewModel
import com.thoughtworks.car.dashboard.ui.navi.NaviView
import com.thoughtworks.car.ui.theme.Dimensions
import com.thoughtworks.car.ui.theme.Theme

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        NaviView(uiStateFlow = uiState.naviViewState)
    }
}