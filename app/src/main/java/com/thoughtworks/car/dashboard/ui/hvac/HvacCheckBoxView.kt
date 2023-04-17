package com.thoughtworks.car.dashboard.ui.hvac

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.blindhmi.ui.component.item.Item
import com.thoughtworks.blindhmi.ui.composable.center
import com.thoughtworks.blindhmi.ui.composable.checkbox.ComposeBlindHMICheckBoxGroup
import com.thoughtworks.blindhmi.ui.composable.indicator
import com.thoughtworks.blindhmi.ui.composable.item
import com.thoughtworks.car.R
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.ui.R as CAR_UIR

const val CHECKBOX_ITEM_SPAN = 32f
const val FLOAT_360 = 360f

@Composable
fun HvacCheckBoxGroup() {
    val context = LocalContext.current
    val viewModel: HvacCheckBoxViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    var checkedSet by remember { mutableStateOf(setOf<Int>()) }

    ComposeBlindHMICheckBoxGroup(
        modifier = Modifier
            .width(dimensionResource(id = CAR_UIR.dimen.dimension_220))
            .height(dimensionResource(id = CAR_UIR.dimen.dimension_153)),
        centerBackgroundRadius = dimensionResource(id = CAR_UIR.dimen.dimension_80),
        centerBackgroundRes = R.drawable.bg_hvac_cb,
        layoutRadius = dimensionResource(id = CAR_UIR.dimen.dimension_48),
        indicator = {
            indicator(context) {
                imageRes = R.drawable.ic_pointer_144
                drawOrder = getDrawOrder() + 1
                radius = context.resources.getDimensionPixelSize(CAR_UIR.dimen.dimension_48)
                visible = false
                onActive = { setVisible(true) }
                onInActive = { setVisible(false) }
            }
        },
        center = {
            center(context) {
                contentFactory = {
                    Image(
                        painter = painterResource(R.drawable.ic_hvac_cb_center),
                        contentDescription = "",
                    )
                }
                drawOrder = getDrawOrder() + 1
            }
        },
        items = createItemsList(context),
        checkStateList = checkedSet,
        onItemSelected = {
            Logger.i("onItemSelected = ${it.getLabel()}")
            when (it.getLabel()) {
                HvacOption.FRONT_WINDOW_DEFROSTER.name -> viewModel.toggleFrontWindowDefroster()
                HvacOption.REAR_WINDOW_DEFROSTER.name -> viewModel.toggleRearWindowDefroster()
                HvacOption.SIDE_MIRROR_HEAT.name -> viewModel.toggleSideMirrorHeat()
                HvacOption.RECIRCULATION_MODE_ON.name, HvacOption.RECIRCULATION_MODE_OFF.name ->
                    viewModel.switchRecirculationModeMode()
            }
        }
    )

    LaunchedEffect(uiState) {
        checkedSet = viewModel.createCheckedSet(uiState)
    }
}

private fun createItemsList(context: Context): List<Item> {
    val itemCount = HvacOption.values().size
    val startAngle = FLOAT_360 / itemCount / 2
    val stepAngle = FLOAT_360 / itemCount

    return HvacOption.values().mapIndexed { index, item ->
        createItem(context, (startAngle + index * stepAngle), item.resId, item.name)
    }
}

private fun createItem(
    context: Context,
    angle: Float,
    @DrawableRes imageRes: Int,
    label: String
): Item {
    return item(context) {
        this.angle = angle
        this.span = CHECKBOX_ITEM_SPAN
        this.imageRes = imageRes
        this.checked = false
        this.label = label
        size = context.resources.getDimensionPixelSize(com.thoughtworks.car.ui.R.dimen.dimension_28)
    }
}

@Preview
@Composable
fun PreViewHvacRadioGroup() {
    HvacCheckBoxGroup()
}
