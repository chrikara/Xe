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

            is XeEvent.OnChangeLocationText -> TODO()
            is XeEvent.OnChangePriceText -> TODO()
            is XeEvent.OnChangeDescriptionText -> TODO()
            XeEvent.OnClearClicked -> TODO()
            XeEvent.OnDialogDismissClicked -> TODO()
            is XeEvent.OnSubmitClicked -> TODO()
        }
    }
}