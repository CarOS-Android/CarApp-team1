package com.thoughtworks.car.dashboard.ui.brake

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Box(
        modifier = modifier.clickable { toggleAutoHold() },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.auto_hold_background),
            contentDescription = null,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.auto_hold),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.padding(vertical = 3.dp))
            Image(
                painter = painterResource(
                    id = if (uiState.autoHoldState) {
                        R.drawable.auto_hold_on
                    } else {
                        R.drawable.auto_hold_off
                    }
                ),
                contentDescription = null,
            )
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
