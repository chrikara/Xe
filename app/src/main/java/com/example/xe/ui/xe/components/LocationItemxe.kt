package com.example.xe.ui.xe.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.xe.ui.theme.LocalSpacing
import com.example.xe.utils.WindowType

@Composable
fun LocationItemXe(
    modifier : Modifier = Modifier,
    text : String,
    onClick : () -> Unit,
    windowType: WindowType
) {
    val spacing = LocalSpacing.current

    Text(
        modifier = modifier
            .padding(spacing.spaceSmall)
            .clickable { onClick() }
            .padding(spacing.spaceSmall)

        ,
        text = text,
        style = if(windowType == WindowType.Mobile) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis

    )

}