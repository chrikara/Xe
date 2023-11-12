package com.example.xe.ui.xe

data class XeState(
    val title : String = "",
    val location : String = "",
    val price : String = "",
    val description : String = "",
    val placeId : String = "",

    val errorTitleText : String = "",
    val hasErrorTitleTextField : Boolean = false,

    )