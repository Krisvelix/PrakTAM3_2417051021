package com.example.praktam_2417051021.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = PurplePrimary,
    secondary = PurpleSecondary,
    background = BackgroundColor,
    surface = CardColor

)

@Composable
fun PrakTAM_2417051021Theme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}