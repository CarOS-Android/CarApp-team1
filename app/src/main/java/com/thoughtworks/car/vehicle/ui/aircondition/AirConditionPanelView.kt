package com.thoughtworks.car.vehicle.ui.aircondition

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R
import com.thoughtworks.car.dashboard.ui.hvac.HvacCheckBoxGroup

@Composable
fun AirConditionPanelView() {
    Row {
        Row(Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.fake_aircondition_panel),
                contentDescription = ""
            )
        }

        Column(Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(painter = painterResource(id = R.drawable.fake_air_direction_up), contentDescription = "")
            Spacer(modifier = Modifier.height(26.dp))
            Image(painter = painterResource(id = R.drawable.fake_air_direction_down), contentDescription = "")
            Spacer(modifier = Modifier.height(84.dp))
            HvacCheckBoxGroup()
        }

        Row(Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.fake_aircondition_panel),
                contentDescription = ""
            )
        }
    }
}