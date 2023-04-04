package com.thoughtworks.car.dashboard.ui.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.ui.theme.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TimeView(modifier: Modifier = Modifier, timeUiState: StateFlow<TimeUiState>) {
    val uiState by timeUiState.collectAsState()
    Column(
        modifier = modifier.height(120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = uiState.time,
            color = Theme.colors.onBackground,
            style = Theme.typography.h2.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview
@Composable
fun PreviewTimeView() {
    TimeView(timeUiState = MutableStateFlow(TimeUiState("10:34")))
}
