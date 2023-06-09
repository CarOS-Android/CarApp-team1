package com.thoughtworks.car.dashboard.ui.ignition

import android.car.VehicleIgnitionState.ACC
import android.car.VehicleIgnitionState.ON
import android.car.VehicleIgnitionState.START
import android.car.VehicleIgnitionState.UNDEFINED
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun IgnitionView(
    modifier: Modifier = Modifier,
    ignitionUiState: StateFlow<Int>
) {
    val uiState by ignitionUiState.collectAsState()
    Box(modifier = modifier.size(dimensionResource(com.thoughtworks.car.ui.R.dimen.dimension_200))) {
        Image(
            painter = painterResource(
                id = when (uiState) {
                    ON, START, ACC -> R.drawable.ic_engine_start
                    else -> R.drawable.ic_engine_stop
                }
            ),
            contentDescription = "",
        )
    }
}

@Preview
@Composable
fun PreviewIgnitionView() {
    IgnitionView(
        ignitionUiState = MutableStateFlow(UNDEFINED)
    )
}
