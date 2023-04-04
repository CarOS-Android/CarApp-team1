package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.door.DoorUseCase
import com.thoughtworks.car.dashboard.ui.navi.NaviUseCase
import com.thoughtworks.car.dashboard.ui.time.TimeUseCase
import com.thoughtworks.car.dashboard.ui.voice.VoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val timeUseCase: TimeUseCase,
    val voiceUseCase: VoiceUseCase,
    val doorUseCase: DoorUseCase,
    val naviUseCase: NaviUseCase,
    // add more useCase here
) : ViewModel()
