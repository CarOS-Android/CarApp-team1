package com.thoughtworks.car.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SettingsUiState(val label: String)

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(SettingsUiState(label = "This is Settings Fragment"))
}