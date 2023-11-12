package com.example.xe.ui.xe.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.example.xe.utils.WindowType


@Composable
fun ButtonXe(
    modifier: Modifier = Modifier,
    text: String = "Submit",
    onClick: () -> Unit = {},
    color : Color,
    windowType: WindowType
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),

        shape = RoundedCornerShape(8.dp)
    ) {

        Text(
            modifier = Modifier.padding(if(windowType == WindowType.Mobile) 5.dp else 15.dp),
            text = text,
            style = if(windowType == WindowType.Mobile) MaterialTheme.typography.labelLarge else MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background,
        )
    }
}