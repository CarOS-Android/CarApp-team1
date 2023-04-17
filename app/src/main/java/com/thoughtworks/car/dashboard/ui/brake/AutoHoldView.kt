package com.thoughtworks.car.dashboard.ui.brake

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.thoughtworks.car.ui.R as CAR_UIR

private const val BLACK_COLOR = 0xFF1B1C1F
private const val GREY_COLOR = 0xFF2E3439

@Composable
fun AutoHoldView(
    autoHoldUiState: StateFlow<AutoHoldUiState>,
    toggleAutoHold: () -> Unit,
) {
    val uiState by autoHoldUiState.collectAsState()

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
            .width(dimensionResource(CAR_UIR.dimen.dimension_118))
            .height(dimensionResource(CAR_UIR.dimen.dimension_90))
            .clickable(onClick = { toggleAutoHold() }),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            Column(
                modifier = Modifier.padding(14.dp, 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.auto_hold),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Image(
                    painter = painterResource(
                        id = if (uiState.autoHoldState) {
                            R.drawable.auto_hold_on
                        } else {
                            R.drawable.auto_hold_off
                        }
                    ),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAutoHoldView() {
    AutoHoldView(
        autoHoldUiState = MutableStateFlow(
            AutoHoldUiState()
        ),
        toggleAutoHold = {
        }
    )
}
