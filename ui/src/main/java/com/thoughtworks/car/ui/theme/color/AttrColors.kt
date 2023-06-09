package com.thoughtworks.car.ui.theme.color

import androidx.annotation.AttrRes
import com.thoughtworks.car.ui.R

data class AttrColors(
    // Material attributes
    @AttrRes val primary: Int = com.google.android.material.R.attr.colorPrimary,
    @AttrRes val primaryVariant: Int = com.google.android.material.R.attr.colorPrimaryVariant,
    @AttrRes val secondary: Int = com.google.android.material.R.attr.colorSecondary,
    @AttrRes val secondaryVariant: Int = com.google.android.material.R.attr.colorSecondaryVariant,
    @AttrRes val background: Int = com.google.android.material.R.attr.backgroundColor,
    @AttrRes val surface: Int = com.google.android.material.R.attr.colorSurface,
    @AttrRes val error: Int = com.google.android.material.R.attr.colorError,
    @AttrRes val onPrimary: Int = com.google.android.material.R.attr.colorOnPrimary,
    @AttrRes val onSecondary: Int = com.google.android.material.R.attr.colorOnSecondary,
    @AttrRes val onBackground: Int = com.google.android.material.R.attr.colorOnBackground,
    @AttrRes val onSurface: Int = com.google.android.material.R.attr.colorOnSurface,
    @AttrRes val onError: Int = com.google.android.material.R.attr.colorOnError,
    // Customize attributes
    @AttrRes val backgroundBlue: Int = R.attr.APPColor_BackgroundBlue,
    @AttrRes val backgroundGreen: Int = R.attr.APPColor_BackgroundGreen
)
