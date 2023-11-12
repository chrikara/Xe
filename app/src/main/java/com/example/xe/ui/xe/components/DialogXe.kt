package com.example.xe.ui.xe.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.xe.utils.WindowType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogXe(
    modifier : Modifier = Modifier,
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    windowType: WindowType
)  {

    val isMobile = windowType == WindowType.Mobile

    AlertDialog(
        modifier = modifier,
        icon = {
            TrailingIconCheck()
        },
        title = {
            Text(
                text = dialogTitle,
                color = MaterialTheme.colorScheme.primary,
                style = if(isMobile) LocalTextStyle.current else MaterialTheme.typography.headlineMedium
            )

        },
        text = {
            Text(
                text = dialogText,
                color = MaterialTheme.colorScheme.primary,
                style = if(isMobile) LocalTextStyle.current else MaterialTheme.typography.titleLarge
            )

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {

        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}