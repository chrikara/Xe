package com.example.xe.ui.xe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xe.utils.WindowType

@Composable
fun TextFieldXe(
    modifier : Modifier = Modifier,
    windowType: WindowType,
    value : String,
    onValueChange : (String) -> Unit,
    minLines : Int = 1,
    isError : Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textError : String = "",
    trailingIcon :  @Composable() (() -> Unit)? = null,
    singleLine : Boolean = if(windowType == WindowType.Mobile) false else true
) {


    TextField(
        value = value,
        modifier = modifier,
        textStyle = if(windowType == WindowType.Mobile) LocalTextStyle.current else MaterialTheme.typography.headlineMedium,
        onValueChange = onValueChange,
        placeholder = {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ){

                Text(
                    style = if(windowType == WindowType.Mobile) LocalTextStyle.current else MaterialTheme.typography.titleLarge,
                    text = "Type something...")


            }
            }
           ,
        singleLine = singleLine,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.primary,
            errorTextColor = MaterialTheme.colorScheme.primary,
            errorContainerColor = MaterialTheme.colorScheme.onSurface

        ),
        isError = isError,
        trailingIcon = trailingIcon,
        supportingText = {
            if(isError){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = textError,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = keyboardOptions,
        minLines = minLines,

        )

}