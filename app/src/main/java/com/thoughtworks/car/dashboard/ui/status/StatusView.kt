package com.thoughtworks.car.dashboard.ui.status

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.car.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun StatusView(
    modifier: Modifier = Modifier,
    statusUiState: StateFlow<StatusUiState>,
    toggleWindowLock: () -> Unit,
) {
    val uiState by statusUiState.collectAsState()

    val configurations = listOf(
        StatusConfigration(
            R.drawable.ic_window_lock,
            R.drawable.ic_window_unlock,
            uiState.windowLockState,
            toggleWindowLock
        ),
        StatusConfigration(R.drawable.ic_fragrance, R.drawable.ic_fragrance, true) {},
        StatusConfigration(R.drawable.ic_wifi, R.drawable.ic_wifi, true) {},
        StatusConfigration(R.drawable.ic_bluetooth, R.drawable.ic_bluetooth, true) {},
        StatusConfigration(R.drawable.ic_cellular, R.drawable.ic_cellular, true) {},
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (configuration in configurations) {
            StatusButton(
                iconOn = configuration.iconOn,
                iconOff = configuration.iconOff,
                status = configuration.status
            ) {
                configuration.onClick()
            }
        }
    }
}

@Composable
fun StatusButton(
    @DrawableRes iconOn: Int,
    @DrawableRes iconOff: Int,
    status: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
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
fun PreviewStatusView() {
    StatusView(
        statusUiState = MutableStateFlow(StatusUiState()),
        toggleWindowLock = {}
    )
}
