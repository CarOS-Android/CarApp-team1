package com.thoughtworks.car.dashboard.ui.voice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VoiceView(modifier: Modifier = Modifier, voiceUiState: VoiceUiState) {
    Row(
        modifier = modifier.height(120.dp)
    ) {
        Image(
            painter = painterResource(id = voiceUiState.voiceAssist),
            contentDescription = "Voice Assist"
        )
        Image(
            painter = painterResource(id = voiceUiState.systemVolume),
            contentDescription = "System Volume"
        )
    }
}

@Preview
@Composable
fun PreviewVoiceView() {
    VoiceView(voiceUiState = VoiceUiState())
}
