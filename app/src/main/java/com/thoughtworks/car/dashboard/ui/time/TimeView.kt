package com.thoughtworks.car.dashboard.ui.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.car.ui.theme.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TimeView(timeUiState: StateFlow<TimeUiState>) {
    val uiState by timeUiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = uiState.time,
            color = Theme.colors.onBackground,
            style = Theme.typography.h1
        )
    }
}

@Preview
@Composable
fun PreviewTimeView() {
    TimeView(timeUiState = MutableStateFlow(TimeUiState("10:34")))
}
