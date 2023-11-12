package com.example.xe.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalConfiguration


data class WindowHeight(
    val type: WindowType,
)

sealed class WindowType{
    data object Mobile : WindowType()
    data object Expanded : WindowType()
}

@Composable
fun rememberWindowSize(): WindowHeight {
    val configuration = LocalConfiguration.current
    val screenHeight by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenHeightDp)
    }

    return WindowHeight(
        type = getScreenHeight(screenHeight),
    )
}

fun getScreenHeight(height: Int): WindowType = when {
    height < 780 -> WindowType.Mobile
    else -> WindowType.Expanded
}

