package com.thoughtworks.car.dashboard.ui.door

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DoorView(
    modifier: Modifier = Modifier,
    doorUiState: StateFlow<DoorUiState>,
    toggleSeatDoors: () -> Unit,
    toggleHoodDoor: () -> Unit,
    toggleRearDoor: () -> Unit
) {
    val uiState by doorUiState.collectAsState()
    ConstraintLayout(modifier = modifier) {
        val car = createRef()
        val (doorButton, doorHoodButton, doorRearButton) = createRefs()
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .constrainAs(doorRearButton) {
                    top.linkTo(parent.top, margin = 70.dp)
                    end.linkTo(parent.end, margin = 70.dp)
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
                .width(440.dp)
                .constrainAs(car) {
                    top.linkTo(doorRearButton.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 70.dp)
                    end.linkTo(doorRearButton.end)
                },
            painter = painterResource(id = uiState.car), contentDescription = ""
        )

        IconButton(
            modifier = Modifier
                .size(50.dp)
                .constrainAs(doorHoodButton) {
                    top.linkTo(parent.top, margin = 100.dp)
                    start.linkTo(parent.start, margin = 80.dp)
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
                .width(104.dp)
                .height(50.dp)
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
        toggleRearDoor = {}
    )
}
