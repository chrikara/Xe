package com.example.xe.ui.xe

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.xe.R
import com.example.xe.ui.theme.LocalSpacing
import com.example.xe.ui.xe.components.TextFieldXe
import com.example.xe.ui.xe.components.TextViewXe
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
    val focusRequester = remember { FocusRequester() }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ){
        item {
            TextViewXe(
                text = stringResource(R.string.new_property_classified),
                style = if(windowType == WindowType.Mobile) MaterialTheme.typography.titleLarge else MaterialTheme.typography.headlineLarge,
                windowType = windowType
            )

            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            TextViewXe(text = stringResource(R.string.title), windowType = windowType)

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                TextViewXe(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = "Location",
                    windowType = windowType
                )

                if(state.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(if(windowType==WindowType.Mobile) 20.dp else 30.dp),
                        strokeWidth = 3.dp
                    )
                }
            }



            if(state.listFromApi.isNotEmpty()){

                LazyColumn(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.onSurface),

                    ){
                    items(state.listFromApi){
                        /*
                          LocationItemXe(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusable()
                                .testTag("LocationItem"),
                            text = "${it.mainText}, ${it.secondaryText}",
                            onClick = {
                                onEvent(XeEvent.OnLocationItemClicked(searchDto = it))

                            }
                        )
                         */

                    }
                }
            }

                Spacer(modifier = Modifier.height(spacing.spaceSmall))



                TextFieldXe(
                    value = state.location,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .testTag("Location"),
                    trailingIcon = if(state.isLocationValid) trailingIconCheck else null,
                    isError = state.hasErrorLocationTextField,
                    textError = state.errorLocationText,
                    windowType = windowType,
                    onValueChange = { onEvent(XeEvent.OnChangeLocationText(it))
                    }
                )


                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                TextViewXe(
                    text = "Price",
                    modifier = Modifier.fillMaxWidth(),
                    windowType = windowType
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                TextFieldXe(
                    value = state.price,
                    modifier = Modifier.fillMaxWidth().testTag("Price"),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    windowType = windowType,
                    onValueChange = {onEvent(XeEvent.OnChangePriceText(it))}
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                TextViewXe(
                    text = "Description",
                    modifier = Modifier.fillMaxWidth(),
                    windowType = windowType,
                    )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                TextFieldXe(
                    value = state.description,
                    modifier = Modifier.fillMaxWidth().testTag("Description"),
                    onValueChange = {onEvent(XeEvent.OnChangeDescriptionText(it))},
                    minLines = 3,
                    windowType = windowType,
                    singleLine = false
                    )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))




        }

    }








}