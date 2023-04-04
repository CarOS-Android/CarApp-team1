package com.thoughtworks.car.dashboard.ui.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StatusView(modifier: Modifier = Modifier, statusUiState: StatusUiState) {
    Box(modifier = modifier) {
        Image(painter = painterResource(id = statusUiState.image), contentDescription = "")
    }
}

@Preview
@Composable
fun PreviewStatusView() {
    StatusView(statusUiState = StatusUiState())
}
