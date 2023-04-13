package com.thoughtworks.car.dashboard.ui.light

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.StateFlow
import com.thoughtworks.car.ui.R as CAR_UIR

@Composable
fun LightView(
    modifier: Modifier = Modifier,
    switchCenterAreaState: () -> Unit,
    lightUiState: StateFlow<LightUiState>,
    toggleHazardLight: () -> Unit
) {
    val uiState by lightUiState.collectAsState()
    Column() {
        Image(
            modifier = modifier.clickable { switchCenterAreaState() },
            painter = painterResource(id = R.drawable.car_light_controller), contentDescription = ""
        )
        IconButton(
            onClick = toggleHazardLight,
            modifier.padding(top = dimensionResource(CAR_UIR.dimen.dimension_24))
        ) {
            Icon(
                painter = if (uiState.hazardLightState) {
                    painterResource(id = R.drawable.hazard_light_on)
                } else {
                    painterResource(id = R.drawable.hazard_light_off)
                },
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}