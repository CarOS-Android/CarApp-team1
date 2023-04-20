package com.thoughtworks.car.vehicle.ui.aircondition

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import com.thoughtworks.car.vehicle.theme.Blue
import com.thoughtworks.car.vehicle.theme.DarkGray
import com.thoughtworks.car.vehicle.theme.LightBlue
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AirConditionControlView(
    airConditionControlUiState: StateFlow<AirConditionControlUiState>,
    toggleHvacPowerFeature: () -> Unit,
    toggleHvacAcFeature: () -> Unit,
    toggleHvacAutoFeature: () -> Unit,
    toggleFragranceFeature: () -> Unit,
) {
    val uiState by airConditionControlUiState.collectAsState()
    AirConditionFeatureButton(
        iconId = R.drawable.ic_ac_button,
        status = uiState.powerState,
        onClick = { toggleHvacPowerFeature() }
    )
    Spacer(modifier = Modifier.height(10.dp))
    AirConditionFeatureButton(
        iconId = R.drawable.ic_ac_text,
        status = uiState.acState,
        isEnable = uiState.powerState && !uiState.autoState,
        onClick = { toggleHvacAcFeature() }
    )
    Spacer(modifier = Modifier.height(10.dp))
    AirConditionFeatureButton(
        iconId = R.drawable.ic_ac_auto,
        status = uiState.autoState,
        isEnable = uiState.powerState,
        onClick = { toggleHvacAutoFeature() }
    )
    Spacer(modifier = Modifier.height(10.dp))
    AirConditionFeatureButton(
        iconId = R.drawable.ic_ac_fragrance,
        status = uiState.fragranceState,
        onClick = { toggleFragranceFeature() }
    )
}

@Composable
private fun AirConditionFeatureButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    status: Boolean,
    isEnable: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clickable(enabled = isEnable, onClick = onClick)
            .wrapContentSize(),
        contentAlignment = Alignment.Center

    ) {
        val bgColor = if (status && isEnable) {
            Blue
        } else if (status && !isEnable) {
            LightBlue
        } else {
            DarkGray
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_ac_bg),
            contentDescription = null,
            tint = bgColor
        )
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null
        )
    }
}