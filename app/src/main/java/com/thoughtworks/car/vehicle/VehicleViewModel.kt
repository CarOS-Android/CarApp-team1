package com.thoughtworks.car.vehicle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class VehicleUiState(val label: String)

@HiltViewModel
class VehicleViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(VehicleUiState(label = "This is Vehicle Fragment"))
}