package com.thoughtworks.car.dashboard.ui.light

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
    toggleHazardLight: () -> Unit,
    toggleHeadLight: () -> Unit,
    toggleHighBeamLight: () -> Unit
) {
    val uiState by lightUiState.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = modifier.clickable { switchCenterAreaState() },
            painter = painterResource(id = R.drawable.car_light_controller), contentDescription = ""
        )
        Row(
            Modifier.padding(top = dimensionResource(CAR_UIR.dimen.dimension_24))
        ) {
            LightButton(
                uiState.hazardLightState,
                R.drawable.hazard_light_on,
                R.drawable.hazard_light_off,
                toggleHazardLight
            )
            LightButton(
                uiState.headLightState,
                R.drawable.head_light_on,
                R.drawable.head_light_off,
                toggleHeadLight
            )
            LightButton(
                uiState.highBeamLightState,
                R.drawable.high_beam_light_on,
                R.drawable.high_beam_light_off,
                toggleHighBeamLight
            )
        }
    }
}

@Composable
private fun LightButton(
    lightState: Boolean,
    @DrawableRes lightOnImage: Int,
    @DrawableRes lightOffImage: Int,
    toggleHazardLight: () -> Unit
) {
    IconButton(
        modifier = Modifier.padding(horizontal = dimensionResource(CAR_UIR.dimen.dimension_16)),
        onClick = toggleHazardLight
    ) {
        Icon(
            painter = if (lightState) {
                painterResource(id = lightOnImage)
            } else {
                painterResource(id = lightOffImage)
            },
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}