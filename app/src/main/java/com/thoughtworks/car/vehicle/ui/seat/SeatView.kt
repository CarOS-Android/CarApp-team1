package com.thoughtworks.car.vehicle.ui.seat

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SeatView(
    seatUiState: StateFlow<SeatUiState>,
    toggleSeatHeating: () -> Unit,
    toggleSeatCooling: () -> Unit,
    toggleMassage: () -> Unit,
    toggleSeatAngle: () -> Unit,
    toggleSeatMemory1: () -> Unit,
    toggleSeatMemory2: () -> Unit,
    toggleSeatMemory3: () -> Unit,
    toggleSeatMemoryPlus: () -> Unit,
) {
    val uiState by seatUiState.collectAsState()

    val seatFeatures = listOf(
        SeatState(
            iconOn = R.drawable.ic_seat_heating_on,
            iconOff = R.drawable.ic_seat_heating_off,
            colorOn = com.thoughtworks.car.ui.R.color.red_CF597C,
            text = R.string.seat_heating,
            status = uiState.seatHeatingState,
            level = uiState.seatHeatingLevel,
            onClick = toggleSeatHeating
        ),
        SeatState(
            iconOn = R.drawable.ic_seat_cooling_on,
            iconOff = R.drawable.ic_seat_cooling_off,
            colorOn = com.thoughtworks.car.ui.R.color.light_blue_3476E3,
            text = R.string.seat_cooling,
            status = uiState.seatCoolingState,
            level = uiState.seatCoolingLevel,
            onClick = toggleSeatCooling
        ),
        SeatState(
            iconOn = R.drawable.ic_seat_massage_on,
            iconOff = R.drawable.ic_seat_massage_off,
            colorOn = com.thoughtworks.car.ui.R.color.light_green_59CF8F,
            text = R.string.seat_massage,
            status = uiState.massageState,
            level = uiState.massageLevel,
            onClick = toggleMassage
        )
    )

    val seatMemories = listOf(
        SeatMemoryState(
            iconOn = R.drawable.ic_seat_memory_1,
            iconOff = R.drawable.ic_seat_memory_1,
            status = uiState.seatMemory1State,
            onClick = toggleSeatMemory1
        ),
        SeatMemoryState(
            iconOn = R.drawable.ic_seat_memory_2,
            iconOff = R.drawable.ic_seat_memory_2,
            status = uiState.seatMemory2State,
            onClick = toggleSeatMemory2
        ),
        SeatMemoryState(
            iconOn = R.drawable.ic_seat_memory_3,
            iconOff = R.drawable.ic_seat_memory_3,
            status = uiState.seatMemory3State,
            onClick = toggleSeatMemory3
        ),
        SeatMemoryState(
            iconOn = R.drawable.ic_seat_memory_plus,
            iconOff = R.drawable.ic_seat_memory_plus,
            status = uiState.seatMemoryPlusState,
            onClick = toggleSeatMemoryPlus
        )
    )

    Card(
        Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .background(Color.Black),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            Modifier
                .wrapContentWidth()
                .height(300.dp)
                .background(colorResource(id = com.thoughtworks.car.ui.R.color.grey_161616))
                .padding(start = 32.dp, top = 36.dp)
        ) {
            Column(
                Modifier
                    .padding(top = 25.dp)
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                SeatAngleButton(
                    title = R.string.driver_seat,
                    onClick = toggleSeatAngle
                )
            }
            Column(
                Modifier
                    .width(500.dp)
                    .wrapContentHeight()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .offset(x = 10.dp)
                ) {
                    for (seatFeature in seatFeatures) {
                        SeatFeatureButton(
                            iconOn = seatFeature.iconOn,
                            iconOff = seatFeature.iconOff,
                            text = seatFeature.text,
                            colorOn = seatFeature.colorOn,
                            status = seatFeature.status,
                            level = seatFeature.level
                        ) {
                            seatFeature.onClick()
                        }
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    for ((index, item) in seatMemories.withIndex()) {
                        SeatMemoryButton(
                            modifier = Modifier
                                .offset(x = if (index > 0) (-8 * index).dp else 0.dp),
                            iconOn = item.iconOn,
                            iconOff = item.iconOff,
                            status = item.status
                        ) {
                            item.onClick()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeatAngleButton(@StringRes title: Int, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                id = com.thoughtworks.car.ui.R.color.grey_161616
            )
        ), onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .width(86.dp)
                    .height(108.dp),
                painter = painterResource(id = R.drawable.ic_seat),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = title),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun SeatMemoryButton(
    modifier: Modifier,
    @DrawableRes iconOn: Int,
    @DrawableRes iconOff: Int,
    status: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.size(125.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                id = com.thoughtworks.car.ui.R.color.grey_161616
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

@Composable
fun SeatFeatureButton(
    @DrawableRes iconOn: Int,
    @DrawableRes iconOff: Int,
    @ColorRes colorOn: Int,
    @StringRes text: Int,
    status: Boolean,
    level: Int,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = com.thoughtworks.car.ui.R.color.grey_161616)),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .width(118.dp)
                .background(
                    colorResource(id = com.thoughtworks.car.ui.R.color.grey_292929),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 23.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(43.dp),
                painter = painterResource(id = if (status) iconOn else iconOff),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(3.dp))
            Row {
                Box(
                    modifier = Modifier
                        .width(7.dp)
                        .height(3.dp)
                        .clip(RectangleShape)
                        .background(
                            if (status) {
                                colorResource(id = colorOn)
                            } else {
                                Color.White
                            }
                        )
                )
                Spacer(modifier = Modifier.width(2.dp))
                Box(
                    modifier = Modifier
                        .width(7.dp)
                        .height(3.dp)
                        .clip(RectangleShape)
                        .background(
                            if (status && level > 1) {
                                colorResource(id = colorOn)
                            } else {
                                Color.White
                            }
                        )
                )
                Spacer(modifier = Modifier.width(2.dp))
                Box(
                    modifier = Modifier
                        .width(7.dp)
                        .height(3.dp)
                        .clip(RectangleShape)
                        .background(
                            if (status && level > 2) {
                                colorResource(id = colorOn)
                            } else {
                                Color.White
                            }
                        )
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewSeatView() {
    SeatView(
        seatUiState = MutableStateFlow(SeatUiState()),
        toggleMassage = {},
        toggleSeatCooling = {},
        toggleSeatHeating = {},
        toggleSeatAngle = {},
        toggleSeatMemory1 = {},
        toggleSeatMemory2 = {},
        toggleSeatMemory3 = {},
        toggleSeatMemoryPlus = {}
    )
}

@Preview
@Composable
fun PreviewSeatFeatureButton() {
    SeatFeatureButton(
        iconOn = R.drawable.ic_seat_heating_on,
        iconOff = R.drawable.ic_seat_heating_off,
        colorOn = com.thoughtworks.car.ui.R.color.red_CF597C,
        text = R.string.seat_heating,
        status = true,
        level = 2,
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewSeatMemoryButton() {
    SeatMemoryButton(
        modifier = Modifier,
        iconOn = R.drawable.ic_seat_memory_1,
        iconOff = R.drawable.ic_seat_memory_1,
        status = false,
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewSeatAngleButton() {
    SeatAngleButton(
        title = R.string.driver_seat,
        onClick = {}
    )
}

