package com.thoughtworks.car.dashboard.ui.light

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thoughtworks.car.R

@Composable
fun LightView(modifier: Modifier = Modifier, switchCenterAreaState: () -> Unit) {
    Image(
        modifier = modifier.clickable { switchCenterAreaState() },
        painter = painterResource(id = R.drawable.car_light_controller), contentDescription = ""
    )
}