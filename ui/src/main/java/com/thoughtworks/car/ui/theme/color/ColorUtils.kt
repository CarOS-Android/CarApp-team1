package com.thoughtworks.car.ui.theme.color

import android.content.Context
import android.content.res.ColorStateList
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.MaterialColors

/**
 *  get the attr color from the theme
 */
fun obtainAttrColorToColor(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = Color(obtainAttrColorToColorInt(context, themeId, attrResId))

/**
 *  get the attr color Int from the theme
 */
fun obtainAttrColorToColorInt(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = MaterialColors.getColor(
    ContextThemeWrapper(context, themeId),
    attrResId, Color.White.toArgb()
)

/**
 *   get the attr color from the theme and transform to color state list
 */
fun obtainAttrColorToStateList(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = ColorStateList.valueOf(obtainAttrColorToColorInt(context, themeId, attrResId))
