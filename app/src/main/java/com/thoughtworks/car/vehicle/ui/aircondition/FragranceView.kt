package com.thoughtworks.car.vehicle.ui.aircondition

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.blindhmi.ui.component.item.Item
import com.thoughtworks.blindhmi.ui.composable.ItemDsl
import com.thoughtworks.blindhmi.ui.composable.border
import com.thoughtworks.blindhmi.ui.composable.center
import com.thoughtworks.blindhmi.ui.composable.indicator
import com.thoughtworks.blindhmi.ui.composable.item
import com.thoughtworks.blindhmi.ui.composable.radio.ComposeBlindHMIRadioGroup
import com.thoughtworks.car.R
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.ui.R.dimen as CAR_UIR

const val FLOAT_360 = 360f

@Composable
fun FragranceView(modifier: Modifier = Modifier) {
    val viewModel: FragranceViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    Row(modifier = modifier) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_seat_top_view),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.padding(
                    top = dimensionResource(CAR_UIR.dimension_90),
                    start = dimensionResource(id = CAR_UIR.dimension_68)
                ),
                painter = painterResource(id = R.drawable.img_fragrance_pointer),
                contentDescription = ""
            )
        }
        Column {
            FragrancePanel(uiState.mainDrivingSeat)
            FragrancePanel(uiState.copilotDrivingSeat)
            FragrancePanel(uiState.rearSeat)
        }
    }
}

@Composable
fun FragrancePanel(currentSelection: FRAGRANCE) {

    val context = LocalContext.current
    ComposeBlindHMIRadioGroup(
        centerBackgroundRadius = dimensionResource(id = CAR_UIR.dimension_80),
        centerBackgroundRes = R.drawable.img_fragrance_center,
        layoutRadius = dimensionResource(id = CAR_UIR.dimension_24),
        currentSelectedItemLabel = currentSelection.name,
        indicator = {
            indicator(context) {
                imageRes = R.drawable.ic_pointer_144
                drawOrder = getDrawOrder() + 1
                radius =
                    context.resources.getDimensionPixelSize(CAR_UIR.dimension_48)
                visible = false
                onActive = { setVisible(true) }
                onInActive = { setVisible(false) }
            }
        },
        center = {
            center(context) {
                contentFactory = {
                    imageRes = R.drawable.ic_hvac_cb_center
                    radius = context.resources.getDimensionPixelSize(CAR_UIR.dimension_8)
                }
                drawOrder = getDrawOrder() + 2
            }
        },
        items = createItems(context),
        border = {
            border(context) {
                imageRes = R.drawable.img_fragrance_border
                radius =
                    resources.getDimensionPixelSize(com.thoughtworks.car.ui.R.dimen.dimension_72)
                drawOrder = getDrawOrder() - 1
            }
        },
        onItemSelected = {
            Logger.i("onItemSelected $it")
        }
    )
}

private fun createItem(
    context: Context,
    angle: Float,
    span: Float,
    @DrawableRes res: Int,
    label: String = ""
): Item {
    return item(context) {
        this.angle = angle
        this.span = span
        this.imageRes = res
        this.label = label
        this.size = context.resources.getDimensionPixelSize(CAR_UIR.dimension_48)
        this.checked = false
        this.itemType = ItemDsl.ItemType.RADIO_GROUP
    }
}

private fun createItems(context: Context): List<Item> {
    val itemsCount = FRAGRANCE.values().size
    val startAngle = FLOAT_360 / itemsCount / 2
    return FRAGRANCE.values().mapIndexed { index, item ->
        createItem(
            context,
            startAngle + (index * FLOAT_360 / itemsCount),
            FLOAT_360 / itemsCount,
            item.res,
            item.name
        )
    }
}