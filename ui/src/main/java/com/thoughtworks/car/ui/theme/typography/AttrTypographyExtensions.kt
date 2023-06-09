package com.thoughtworks.car.ui.theme.typography

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat

private const val TypographyIndexTextSize = 0
private const val TypographyIndexTypeface = 1
private const val TypographyIndexAllCaps = 2
private const val TypographyIndexFontFamily = 3
private const val TypographyIndexSpacingEm = 4

@SuppressLint("ResourceType")
fun AttrTypography.attributes(context: Context): AttrTypography.Attributes =
    context.obtainStyledAttributes(
        styleRes,
        intArrayOf(
            android.R.attr.textSize,
            android.R.attr.textStyle,
            android.R.attr.textAllCaps,
            android.R.attr.fontFamily,
            android.R.attr.letterSpacing
        )
    ).run {
        val textSizePx = getDimension(TypographyIndexTextSize, 14.sp.value)
        val textSizeSp = geSizeSp(TypographyIndexTextSize, textSizePx)
        val typefaceStyle = getInt(TypographyIndexTypeface, Typeface.NORMAL)
        val allCaps = getBoolean(TypographyIndexAllCaps, false)
        val fontFamily = getString(TypographyIndexFontFamily) ?: FontFamily.Default.toString()
        val letterSpacingEm = getFloat(TypographyIndexSpacingEm, 0f)

        recycle()
        AttrTypography.Attributes(
            textSizePx,
            textSizeSp,
            fontFamily,
            allCaps,
            typefaceStyle,
            letterSpacingEm
        )
    }

private fun TypedArray.geSizeSp(index: Int, defaultValue: Float) =
    getString(index)?.replace("sp", "")?.toFloat() ?: defaultValue

internal fun AttrTypography.style(context: Context): TextStyle = with(attributes(context)) {
    val fontWeight =
        if (typefaceStyle == Typeface.NORMAL) FontWeight.Normal else FontWeight.Bold
    val fontStyle = if (typefaceStyle == Typeface.ITALIC) FontStyle.Italic else FontStyle.Normal

    TextStyle(
        fontSize = textSizeSp.sp,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        letterSpacing = letterSpacingEm.em,
        fontFamily = createTypeface(context, this)?.let { FontFamily(it) },
    )
}

private fun createTypeface(
    context: Context,
    attributes: AttrTypography.Attributes,
): Typeface? = if (attributes.fontFamily.isFontFamilyPath()) {
    val id = context.resources.getIdentifier(
        attributes.fontFamily.getFontFamilyName(), "font", context.packageName
    )
    ResourcesCompat.getFont(context, id)
} else {
    // system's font here
    Typeface.create(attributes.fontFamily, attributes.typefaceStyle)
}

private fun String.isFontFamilyPath() = contains("res/font") && endsWith(".ttf")

private fun String.getFontFamilyName(): String =
    substringAfterLast("res/font/").substringBeforeLast(".ttf")
