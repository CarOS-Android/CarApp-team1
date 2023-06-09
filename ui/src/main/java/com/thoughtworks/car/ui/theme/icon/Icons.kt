package com.thoughtworks.car.ui.theme.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.thoughtworks.car.ui.R

object Icons {
    val ArrowBack = R.drawable.ic_arrow_back
    val ArrowForward = R.drawable.ic_arrow_forward
    val Close = R.drawable.ic_close
    val Cancel = R.drawable.ic_cancel
    val Delete = R.drawable.ic_delete
    val Home = R.drawable.ic_home
    val Menu = R.drawable.ic_menu
    val More = R.drawable.ic_more
    val Favorite = R.drawable.ic_favorite
    val Search = R.drawable.ic_search
    val Dashboard = R.drawable.ic_dashboard
    val Notification = R.drawable.ic_notifications
    val Warning = Icons.Outlined.Warning
    val Info = Icons.Outlined.Info
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}