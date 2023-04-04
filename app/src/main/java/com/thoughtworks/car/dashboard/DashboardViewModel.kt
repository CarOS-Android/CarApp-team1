package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.door.DoorUseCase
import com.thoughtworks.car.dashboard.ui.hvac.HvacUseCase
import com.thoughtworks.car.dashboard.ui.media.MediaUseCase
import com.thoughtworks.car.dashboard.ui.navi.NaviUseCase
import com.thoughtworks.car.dashboard.ui.status.StatusUseCase
import com.thoughtworks.car.dashboard.ui.time.TimeUseCase
import com.thoughtworks.car.dashboard.ui.voice.VoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val timeUseCase: TimeUseCase,
    val voiceUseCase: VoiceUseCase,
    val doorUseCase: DoorUseCase,
    val hvacUseCase: HvacUseCase,
    val naviUseCase: NaviUseCase,
    val mediaUseCase: MediaUseCase,
    val statusUseCase: StatusUseCase
    // add more useCase here
) : ViewModel()
