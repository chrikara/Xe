package com.example.xe.ui.xe

import android.content.Context

sealed class XeEvent {


    data class OnChangeTitleText(val text : String) : XeEvent()
    data class OnChangeLocationText(val query : String) : XeEvent()
    data class OnChangePriceText(val text : String) : XeEvent()
    data class OnChangeDescriptionText(val text : String) : XeEvent()

    data object OnClearClicked : XeEvent()
    data class OnSubmitClicked(val context : Context) : XeEvent()
    data object OnDialogDismissClicked : XeEvent()
}