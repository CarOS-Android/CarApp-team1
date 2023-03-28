package com.thoughtworks.car.sample.permission.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.car.core.permission.SinglePermission

@Composable
internal fun SinglePermissionWithDialogScreen() {
    SinglePermission(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        beforeRequestContent = {
            PermissionPlaceHolder(it)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CommonText(
                text = "The location permission already granted!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}