package com.thoughtworks.car.sample.sound.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.car.media.sound.alert.AlertItem
import com.thoughtworks.car.media.sound.media.MediaItem
import com.thoughtworks.car.media.sound.tts.TTSItem
import com.thoughtworks.car.sample.R
import com.thoughtworks.car.sample.sound.SoundViewModel
import com.thoughtworks.car.ui.component.AppFilledButton
import com.thoughtworks.car.ui.theme.Dimensions
import com.thoughtworks.car.ui.theme.Theme

@Composable
fun SoundScreen(viewModel: SoundViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding)
    ) {
        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.playAlert(AlertItem.fromAsset("wind_bell.mp3")) },
            text = {
                Text(text = "Play alert sound 1")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.playAlert(AlertItem.fromRes(R.raw.guitar)) },
            text = {
                Text(text = "Play alert sound 2")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.playMedia(MediaItem.fromAsset("music.mp3")) },
            text = {
                Text(text = "Play music")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.playTts(TTSItem("hello world!")) },
            text = {
                Text(text = "TTS speak text")
            }
        )
    }
}