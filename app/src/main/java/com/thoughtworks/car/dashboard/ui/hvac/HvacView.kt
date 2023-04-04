package com.thoughtworks.car.dashboard.ui.hvac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import com.thoughtworks.car.ui.theme.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HvacView(
    modifier: Modifier = Modifier,
    hvacUiState: StateFlow<HvacUiState>,
//    setLeftTemperature: (Float) -> Unit,
//    setRightTemperature: (Float) -> Unit
) {
    val uiState by hvacUiState.collectAsState()
    Box(modifier = modifier) {
        Image(painter = painterResource(id = R.drawable.bg_hvac), contentDescription = "")
        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.size(100.dp),
                text = uiState.leftTemperature,
                color = Theme.colors.onBackground,
                style = Theme.typography.body01
            )
            Spacer(modifier = Modifier.width(80.dp))
            Text(
                modifier = Modifier.size(100.dp),
                text = uiState.rightTemperature,
                color = Theme.colors.onBackground,
                style = Theme.typography.body01
            )
        }
    }
}

@Preview
@Composable
fun PreviewHvacView() {
    HvacView(
        hvacUiState = MutableStateFlow(
            HvacUiState(
                leftTemperature = "10\u00b0",
                rightTemperature = "15\u00b0"
            )
        ),
//        setLeftTemperature = {},
//        setRightTemperature = {}
    )
}
