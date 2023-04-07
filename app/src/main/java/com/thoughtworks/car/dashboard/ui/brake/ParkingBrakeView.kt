package com.thoughtworks.car.dashboard.ui.brake

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val BLACK_COLOR = 0xFF32383F
private const val GREY_COLOR = 0xFF1C1E24
private const val SCALE_RATIO = 0.67f

@Composable
fun ParkingBrakeView(
    parkingBrakeUiState: StateFlow<ParkingUiState>,
    toggleParkingBrake: () -> Unit
) {
    val uiState by parkingBrakeUiState.collectAsState()

    val brush = Brush.linearGradient(
        colors = listOf(Color(BLACK_COLOR), Color(GREY_COLOR)),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .background(
                brush = brush,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = { toggleParkingBrake() }),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            Column(
                modifier = Modifier.padding(14.dp, 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.parking_brake_p),
                    contentDescription = null,
                    modifier = Modifier.scale(SCALE_RATIO)
                )
                Spacer(modifier = Modifier.padding(vertical = 1.dp))
                Image(
                    painter = painterResource(
                        id = if (uiState.parkingBrakeUiState) {
                            R.drawable.auto_hold_on
                        } else {
                            R.drawable.auto_hold_off
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.scale(SCALE_RATIO)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoorView() {
    ParkingBrakeView(
        parkingBrakeUiState = MutableStateFlow(
            ParkingUiState()
        ),
        toggleParkingBrake = {}
    )
}