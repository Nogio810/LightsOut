package com.example.lightsout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SettingIconButton(
    darkTheme: Boolean,
    lightIcon: Int,
    darkIcon: Int,
    alpha: Float = 1f,
    onClick: (() -> Unit)?
) {
    val iconRes = if (darkTheme) darkIcon else lightIcon

    Box(
        modifier = Modifier
            .size(40.dp)
            .alpha(alpha)
            .clickable(enabled = alpha == 1f && onClick != null) { onClick?.invoke() }
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}