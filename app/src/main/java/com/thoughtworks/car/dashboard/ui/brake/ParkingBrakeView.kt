package com.thoughtworks.car.dashboard.ui.brake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ParkingBrakeView(
    modifier: Modifier = Modifier,
    parkingBrakeUiState: StateFlow<ParkingUiState>,
    toggleParkingBrake:()->Unit) {
    val uiState by parkingBrakeUiState.collectAsState()
    Box(modifier = Modifier.clickable { toggleParkingBrake() },
        contentAlignment = Alignment.Center,) {
        Image(painter =  painterResource(id = R.drawable.parking_barke_background), contentDescription = null)
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            Image(painter = painterResource(id = R.drawable.parking_brake_p), contentDescription = null )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Image(
                painter = painterResource(id = if (uiState.parkingBrakeUiState) R.drawable.auto_hold_on
                else R.drawable.auto_hold_off),
                contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoorView() {
    ParkingBrakeView(parkingBrakeUiState = MutableStateFlow(ParkingUiState()),toggleParkingBrake = {})
}