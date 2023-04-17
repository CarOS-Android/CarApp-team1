package com.thoughtworks.car.vehicle.ui.aircondition

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AirConditionControlView(
    airConditionControlUiState: StateFlow<AirConditionControlUiState>,
    toggleFragranceFeature: () -> Unit,
) {
    val uiState by airConditionControlUiState.collectAsState()

    AirConditionFeatureButton(
        iconOn = R.drawable.ic_air_condition_on_bg,
        iconOff = R.drawable.ic_air_condition_on_bg,
        status = true,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(15.dp))
    AirConditionFeatureButton(
        iconOn = R.drawable.ic_ac_off_bg,
        iconOff = R.drawable.ic_ac_off_bg,
        status = true,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(15.dp))
    AirConditionFeatureButton(
        iconOn = R.drawable.ic_ac_auto_off_bg,
        iconOff = R.drawable.ic_ac_auto_off_bg,
        status = true,
        onClick = {}
    )
    Spacer(modifier = Modifier.height(15.dp))
    AirConditionFeatureButton(
        iconOn = R.drawable.ic_fragrance_on_bg,
        iconOff = R.drawable.ic_fragrance_off_bg,
        status = uiState.fragranceState,
        onClick = toggleFragranceFeature
    )
}

@Composable
fun AirConditionFeatureButton(
    @DrawableRes iconOn: Int,
    @DrawableRes iconOff: Int,
    status: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(116.dp).offset(y = -20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                id = com.thoughtworks.car.ui.R.color.black
            )
        ),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = if (status) iconOn else iconOff),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
fun PreviewFragranceButton() {
    AirConditionFeatureButton(
        iconOff = R.drawable.ic_fragrance_off_bg,
        iconOn = R.drawable.ic_fragrance_on_bg,
        status = true,
        onClick = {}
    )
}
