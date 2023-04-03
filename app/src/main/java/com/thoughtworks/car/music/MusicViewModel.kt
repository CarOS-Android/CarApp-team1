package com.thoughtworks.car.music

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MusicUiState(val label: String)

@HiltViewModel
class MusicViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(MusicUiState(label = "This is Music Fragment"))
}