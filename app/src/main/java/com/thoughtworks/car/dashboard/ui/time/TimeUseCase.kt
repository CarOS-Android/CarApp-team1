package com.thoughtworks.car.dashboard.ui.time

import com.thoughtworks.car.core.ui.BaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

data class TimeUiState(val time: String = "")

class TimeUseCase @Inject constructor() : BaseUseCase {
    private val _uiState: MutableStateFlow<TimeUiState> = MutableStateFlow(TimeUiState())
    val uiState: StateFlow<TimeUiState> = _uiState

    private val timer = Timer()

    init {
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    _uiState.value = TimeUiState(current())
                }
            },
            0, ONE_SECOND
        )
    }

    private fun current(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return current.format(formatter)
    }

    override fun onCleared() {
        timer.cancel()
    }

    companion object {
        const val ONE_SECOND = 1000L
    }
}
