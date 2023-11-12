package com.example.xe.ui.xe

import android.content.Context
import com.example.xe.domain.model.SearchDto

sealed class XeEvent {


    data class OnChangeTitleText(val text : String) : XeEvent()
    data class OnChangeLocationText(val query : String) : XeEvent()
    data class OnChangePriceText(val text : String) : XeEvent()
    data class OnChangeDescriptionText(val text : String) : XeEvent()

    data class OnLocationItemClicked(val searchDto : SearchDto) : XeEvent()

    data object OnClearClicked : XeEvent()
    data object OnSubmitClicked : XeEvent()
    data object OnDialogDismissClicked : XeEvent()
}