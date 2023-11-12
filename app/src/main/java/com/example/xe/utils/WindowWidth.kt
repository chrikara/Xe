package com.example.xe.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalConfiguration


data class WindowWidth(
    val type: WindowType,
)

sealed class WindowType{
    data object Mobile : WindowType()
    data object Expanded : WindowType()
}

@Composable
fun rememberWindowSize(): WindowWidth {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenWidthDp)

    }

    return WindowWidth(
        type = getScreenWidth(screenWidth),
    )
}

fun getScreenWidth(width: Int): WindowType = when {
    width < 600 -> WindowType.Mobile
    else -> WindowType.Expanded
}

