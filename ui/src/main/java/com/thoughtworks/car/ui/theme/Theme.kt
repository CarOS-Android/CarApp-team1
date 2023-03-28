package com.thoughtworks.car.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.car.ui.theme.color.AttrColors
import com.thoughtworks.car.ui.theme.color.Colors
import com.thoughtworks.car.ui.theme.color.LocalColors
import com.thoughtworks.car.ui.theme.color.toColors
import com.thoughtworks.car.ui.theme.typography.LocalTypography
import com.thoughtworks.car.ui.theme.typography.Typography
import com.thoughtworks.car.ui.theme.typography.generateTypographyFromAttributes

@Composable
fun Theme(content: @Composable () -> Unit) {
    val colors = AttrColors().toColors()
    val typography = generateTypographyFromAttributes()

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides Shapes(),
        content = content
    )
}

object Theme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
    val typography: Typography
        @Composable
        get() = LocalTypography.current
    val shapes: Shapes
        @Composable
        get() = LocalShapes.current
}
