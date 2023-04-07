package com.thoughtworks.car.dashboard.ui.hvac

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.blindhmi.ui.component.container.stepper.Stepper
import com.thoughtworks.blindhmi.ui.composable.border
import com.thoughtworks.blindhmi.ui.composable.center
import com.thoughtworks.blindhmi.ui.composable.indicator
import com.thoughtworks.blindhmi.ui.composable.stepper.ComposeBlindHMILoopStepper
import com.thoughtworks.car.ui.R
import com.thoughtworks.car.R as CAR_R

private const val FAN_SPEED_1 = 1
private const val FAN_SPEED_2 = 2
private const val FAN_SPEED_3 = 3
private const val FAN_SPEED_4 = 4

@Composable
fun HvacFanSpeedController() {
    val viewModel: HvacViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val resources = LocalContext.current.resources
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ComposeBlindHMILoopStepper(
            modifier = Modifier.size(dimensionResource(id = R.dimen.dimension_118)),
            centerHotspotRadius = dimensionResource(id = R.dimen.dimension_26),
            centerBackgroundRadius = dimensionResource(id = R.dimen.dimension_52),
            centerBackgroundRes = CAR_R.drawable.ic_fan_speed_center_bg,
            stepOrientation = Stepper.CLOCKWISE,
            steps = 4,
            currentValue = 1f,
            minValue = 0f,
            maxValue = 4f,
            stepValue = 1f,
            startAngle = 0f,
            border = {
                border(context) {
                    imageRes = CAR_R.drawable.ic_fan_speed_center_border
                    expandRadius = resources.getDimensionPixelSize(R.dimen.dimension_52)
                    radius = resources.getDimensionPixelSize(R.dimen.dimension_44)
                    drawOrder = getDrawOrder() - 1
                }
            },
            indicator = {
                indicator(context) {
                    imageRes = CAR_R.drawable.ic_pointer_144
                    drawOrder = getDrawOrder()
                    radius = resources.getDimensionPixelSize(R.dimen.dimension_48)
                    visible = true
                }
            },
            center = {
                center(context) {
                    contentFactory = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(dimensionResource(R.dimen.dimension_128)),
                        ) {
                            Image(
                                painter = painterResource(
                                    id = when (uiState.hvacFanSpeed) {
                                        FAN_SPEED_1 -> CAR_R.drawable.ic_fan_speed1_center
                                        FAN_SPEED_2 -> CAR_R.drawable.ic_fan_speed2_center
                                        FAN_SPEED_3 -> CAR_R.drawable.ic_fan_speed3_center
                                        FAN_SPEED_4 -> CAR_R.drawable.ic_fan_speed4_center
                                        else -> CAR_R.drawable.ic_fan_speed_default_center
                                    }
                                ),
                                contentDescription = "",
                            )
                        }
                    }
                    drawOrder = getDrawOrder() + 1
                }
            },
            onSweepStep = { _, stepperValue ->
                viewModel.setHvacFanSpeed(stepperValue.toInt())
            }
        )
        Text(
            text = "风量",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun PreViewFanSpeedController() {
    HvacFanSpeedController()
}