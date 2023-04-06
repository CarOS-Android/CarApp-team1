package com.thoughtworks.car.dashboard.ui.brake

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

@Composable
fun AutoHoldView(
    modifier: Modifier = Modifier,
    autoHoldUiState: StateFlow<AutoHoldUiState>,
    toggleAutoHold: () -> Unit,
) {
    val uiState by autoHoldUiState.collectAsState()

    val brush = Brush.linearGradient(
        colors = listOf(Color(0xFF1B1C1F), Color(0xFF2E3439)),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .background(
                brush = brush,
                shape = RoundedCornerShape(15.dp)
            )
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
                    contentDescription = null,
                    modifier = Modifier.scale(0.67f)
                )
                Spacer(modifier = Modifier.padding(vertical = 1.dp))
                Image(
                    painter = painterResource(
                        id = if (uiState.autoHoldState) {
                            R.drawable.auto_hold_on
                        } else {
                            R.drawable.auto_hold_off
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.scale(0.67f)
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
