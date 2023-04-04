package com.thoughtworks.car.dashboard.ui.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MediaView(modifier: Modifier = Modifier, mediaUiState: MediaUiState) {
    Box(modifier = modifier) {
        Image(painter = painterResource(id = mediaUiState.image), contentDescription = "")
    }
}

@Preview
@Composable
fun PreviewMediaView() {
    MediaView(mediaUiState = MediaUiState())
}
