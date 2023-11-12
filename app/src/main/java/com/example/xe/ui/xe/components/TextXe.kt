package com.example.xe.ui.xe.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.xe.utils.WindowType


@Composable
fun TextViewXE(
    modifier : Modifier = Modifier,
    text : String,
    windowType : WindowType,
    style: TextStyle = if(windowType == WindowType.Mobile) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleLarge
) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.Black,
        style = style,
        fontWeight = FontWeight.Bold,

    )



}