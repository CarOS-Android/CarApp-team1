package com.thoughtworks.car.vehicle.ui.atmospherelight

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thoughtworks.car.R

@Composable
fun AtmosphereLightView() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.image_atmosphere_light),
        contentDescription = null
    )
}