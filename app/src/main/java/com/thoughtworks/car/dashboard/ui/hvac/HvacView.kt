package com.thoughtworks.car.dashboard.ui.hvac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.blindhmi.ui.component.container.stepper.Stepper
import com.thoughtworks.blindhmi.ui.composable.border
import com.thoughtworks.blindhmi.ui.composable.center
import com.thoughtworks.blindhmi.ui.composable.indicator
import com.thoughtworks.blindhmi.ui.composable.stepper.ComposeBlindHMILoopStepper
import com.thoughtworks.blindhmi.ui.utils.moveDown
import com.thoughtworks.blindhmi.ui.utils.moveUp
import com.thoughtworks.car.R
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.ui.theme.Theme
import java.util.Locale
import com.thoughtworks.car.ui.R as UiR

@Composable
fun HvacView(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            modifier = modifier.size(width = 663.dp, height = 228.dp),
            painter = painterResource(id = R.drawable.bg_hvac),
            contentDescription = ""
        )
        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HvacTemperatureView(
                driverSeat = true,
                description = "主驾",
            )
            HvacFanSpeedController()
            HvacTemperatureView(
                driverSeat = false,
                description = "副驾",
            )
        }
    }
}

@Composable
private fun HvacTemperatureView(driverSeat: Boolean, description: String) {
    val viewModel: HvacViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val resources = LocalContext.current.resources

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val isTemperatureDockVisible = remember { mutableStateOf(false) }

        ComposeBlindHMILoopStepper(
            modifier = Modifier.size(144.dp),
            centerHotspotRadius = 26.dp,
            centerBackgroundRadius = 72.dp,
            centerBackgroundRes = R.drawable.ic_dock_temperature_bg,
            stepOrientation = Stepper.CLOCKWISE,
            steps = 24,
            currentValue = 16f,
            minValue = 16f,
            maxValue = 28f,
            stepValue = 0.5f,
            startAngle = 0f,
            border = {
                border(context) {
                    imageRes = R.drawable.ic_dock_temperature_scale
                    expandRadius = resources.getDimensionPixelSize(UiR.dimen.dimension_72)
                    radius = resources.getDimensionPixelSize(UiR.dimen.dimension_52)
                    drawOrder = getDrawOrder() - 1
                }
            },
            indicator = {
                indicator(context) {
                    imageRes = R.drawable.ic_pointer_144
                    drawOrder = getDrawOrder() + 1
                    radius = resources.getDimensionPixelSize(UiR.dimen.dimension_72)
                    visible = true
                    onActive = {
                        isTemperatureDockVisible.value = true
                    }
                    onInActive = {
                        isTemperatureDockVisible.value = false
                    }
                }
            },
            center = {
                center(context) {
                    contentFactory = {
                        Box(
                            modifier = Modifier.size(128.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.ic_dock_temperature_center
                                ),
                                contentDescription = "",
                            )
                            Text(
                                text = String.format(
                                    Locale.getDefault(),
                                    "%.1f\u00b0",
                                    if (driverSeat) uiState.leftTemperature else uiState.rightTemperature
                                ),
                                color = colorResource(id = UiR.color.grey_800),
                                style = Theme.typography.h6
                            )
                            if (isTemperatureDockVisible.value) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.ic_dock_temperature_cool_off,
                                    ),
                                    contentDescription = "",
                                )
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.ic_dock_temperature_warm_off
                                    ),
                                    contentDescription = "",
                                )
                            }
                        }
                    }
                }
            },
            onActive = { moveDown() },
            onInactive = { moveUp() },
            onSweepStep = { _, value ->
                if (driverSeat) {
                    viewModel.setHvacLeftTemperature(value)
                } else {
                    viewModel.setHvacRightTemperature(value)
                }
            },
            onStepHover = { step ->
                Logger.i("onStepHover: step = $step")
            },
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = description,
            color = Color.Gray,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun PreviewHvacView() {
    HvacView()
}
