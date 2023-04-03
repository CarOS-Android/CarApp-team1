package com.thoughtworks.car.dashboard.ui.navi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NaviView(modifier: Modifier = Modifier, naviUiState: StateFlow<NaviUiState>) {
    val uiState by naviUiState.collectAsState()
    Box(modifier = modifier) {
        Image(painter = painterResource(id = uiState.image), contentDescription = "")
    }
}
