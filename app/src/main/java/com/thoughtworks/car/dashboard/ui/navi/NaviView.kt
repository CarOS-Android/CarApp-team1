package com.thoughtworks.car.dashboard.ui.navi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thoughtworks.car.ui.theme.Dimensions
import com.thoughtworks.car.ui.theme.Theme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NaviView(modifier: Modifier = Modifier, uiStateFlow: StateFlow<NaviViewState>) {
    val uiState by uiStateFlow.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        Image(painter = painterResource(id = uiState.image), contentDescription = "")
    }
}
