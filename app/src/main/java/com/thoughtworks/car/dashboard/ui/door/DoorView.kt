package com.thoughtworks.car.dashboard.ui.door

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.thoughtworks.car.ui.R as CAR_UIR

@Composable
fun DoorView(
    modifier: Modifier = Modifier,
    doorUiState: StateFlow<DoorUiState>,
    toggleSeatDoors: () -> Unit,
    toggleHoodDoor: () -> Unit,
    toggleRearDoor: () -> Unit,
    switchCenterAreaState: () -> Unit
) {
    val uiState by doorUiState.collectAsState()
    ConstraintLayout(modifier = modifier) {
        val car = createRef()
        val (doorButton, doorHoodButton, doorRearButton) = createRefs()
        IconButton(
            modifier = Modifier
                .size(dimensionResource(CAR_UIR.dimen.dimension_50))
                .constrainAs(doorRearButton) {
                    top.linkTo(parent.top, margin = 70.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                },
            onClick = { toggleRearDoor() }
        ) {
            Icon(
                painter = painterResource(id = if (uiState.doorRearState) R.drawable.ic_unlock else R.drawable.ic_lock),
                tint = Color.Unspecified,
                contentDescription = ""
            )
        }

        Image(
            modifier = Modifier
                .size(
                    width = dimensionResource(CAR_UIR.dimen.dimension_625),
                    height = dimensionResource(CAR_UIR.dimen.dimension_220)
                )
                .constrainAs(car) {
                    top.linkTo(doorRearButton.bottom)
                    start.linkTo(parent.start, margin = 18.dp)
                    end.linkTo(doorRearButton.end)
                }
                .clickable { switchCenterAreaState() },
            painter = painterResource(id = uiState.car), contentDescription = ""
        )

        IconButton(
            modifier = Modifier
                .size(dimensionResource(CAR_UIR.dimen.dimension_50))
                .constrainAs(doorHoodButton) {
                    top.linkTo(parent.top, margin = 130.dp)
                    start.linkTo(parent.start)
                },
            onClick = { toggleHoodDoor() }
        ) {
            Icon(
                painter = painterResource(id = if (uiState.doorHoodState) R.drawable.ic_unlock else R.drawable.ic_lock),
                tint = Color.Unspecified,
                contentDescription = ""
            )
        }

        IconButton(
            modifier = Modifier
                .width(dimensionResource(CAR_UIR.dimen.dimension_100))
                .height(dimensionResource(CAR_UIR.dimen.dimension_50))
                .constrainAs(doorButton) {
                    top.linkTo(car.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = { toggleSeatDoors() }
        ) {
            Icon(
                painter = painterResource(
                    id = if (uiState.doorSeatLock) {
                        R.drawable.ic_lock_large
                    } else {
                        R.drawable.ic_unlock_large
                    }
                ),
                tint = Color.Unspecified,
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoorView() {
    DoorView(
        doorUiState = MutableStateFlow(
            DoorUiState(
                doorSeatLock = true,
                doorHoodState = false,
                doorRearState = false,
                car = R.drawable.ic_car
            )
        ),
        toggleSeatDoors = {},
        toggleHoodDoor = {},
        toggleRearDoor = {},
        switchCenterAreaState = {}
    )
}
