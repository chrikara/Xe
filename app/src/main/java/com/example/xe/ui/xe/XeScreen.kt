package com.example.xe.ui.xe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xe.R
import com.example.xe.ui.theme.LocalSpacing
import com.example.xe.ui.xe.components.TextFieldXe
import com.example.xe.ui.xe.components.TextViewXE
import com.example.xe.ui.xe.components.trailingIconCheck
import com.example.xe.utils.WindowType

@Composable
fun XeScreen(
    windowType : WindowType,
    onEvent: (XeEvent) -> Unit,
    state : XeState
) {


    val spacing = LocalSpacing.current
    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ){
        item {
            TextViewXE(
                text = stringResource(R.string.new_property_classified),
                style = if(windowType == WindowType.Mobile) MaterialTheme.typography.titleLarge else MaterialTheme.typography.headlineLarge,
                windowType = windowType
            )

            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            TextViewXE(text = stringResource(R.string.title), windowType = windowType)

            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            TextFieldXe(
                value = state.title,
                windowType = windowType,
                isError = state.hasErrorTitleTextField,
                trailingIcon = if(state.title.isNotBlank()) trailingIconCheck else null,
                textError = state.errorTitleText,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {onEvent(XeEvent.OnChangeTitleText(it))}
            )



        }

    }








}