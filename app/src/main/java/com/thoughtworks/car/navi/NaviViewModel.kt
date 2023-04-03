package com.thoughtworks.car.navi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class NavUiState(val label: String)

@HiltViewModel
class NaviViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(NavUiState(label = "This is Navi Fragment"))
    val uiState = _uiState.asStateFlow()
}