package com.example.xe.ui.xe

import com.example.xe.domain.model.SearchDto

data class XeState(
    val title : String = "",
    val location : String = "",
    val price : String = "",
    val description : String = "",
    val placeId : String = "",

    val listFromApi : List<SearchDto> = emptyList(),

    val errorLocationText : String = "",
    val errorTitleText : String = "",

    val hasErrorTitleTextField : Boolean = false,
    val hasErrorLocationTextField : Boolean = false,
    val isLocationValid : Boolean = false,
    val isLoading : Boolean = false,


    )