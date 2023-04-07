package com.thoughtworks.car.dashboard

import androidx.lifecycle.ViewModel
import com.thoughtworks.car.dashboard.ui.brake.AutoHoldUseCase
import com.thoughtworks.car.dashboard.ui.brake.ParkingBrakeUseCase
import com.thoughtworks.car.dashboard.ui.door.DoorUseCase
import com.thoughtworks.car.dashboard.ui.ignition.IgnitionUseCase
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
    val ignitionUseCase: IgnitionUseCase,
    val doorUseCase: DoorUseCase,
    val naviUseCase: NaviUseCase,
    val mediaUseCase: MediaUseCase,
    val statusUseCase: StatusUseCase,
    val autoHoldUseCase: AutoHoldUseCase,
    val parkingBrakeUseCase: ParkingBrakeUseCase
    // add more useCase here
) : ViewModel()
