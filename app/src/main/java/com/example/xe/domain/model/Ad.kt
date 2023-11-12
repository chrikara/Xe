package com.example.xe.domain.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable

@Serializable
data class Ad(
    val placeId : String = "",
    val title: String = "",
    val location: String = "",

    @EncodeDefault
    val price: String = "",
    @EncodeDefault
    val description: String = ""
)


