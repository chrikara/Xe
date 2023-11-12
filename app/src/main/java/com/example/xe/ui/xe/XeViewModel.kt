package com.example.xe.ui.xe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.xe.domain.usecase.FilterDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class XeViewModel @Inject constructor(
    private val filterDigits: FilterDigits
) : ViewModel(

) {

    var state by mutableStateOf<XeState>(XeState())
        private set

    fun onEvent(event: XeEvent){
        when(event){
            is XeEvent.OnChangeTitleText -> {
                state = state.copy(
                    title = event.text
                )
            }

            is XeEvent.OnChangeLocationText -> {
                state = state.copy(
                    location = event.text
                )
            }
            is XeEvent.OnChangePriceText -> {
                state = state.copy(
                    price = event.text
                )
            }
            is XeEvent.OnChangeDescriptionText -> {
                state = state.copy(
                    description = event.text
                )
            }
            XeEvent.OnClearClicked -> TODO()
            XeEvent.OnDialogDismissClicked -> TODO()
            is XeEvent.OnSubmitClicked -> TODO()
        }
    }










}