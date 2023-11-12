package com.example.xe.data

import com.example.xe.domain.model.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface XeApi {

    @GET("/")
    suspend fun getData(@Query("input") query: String): List<SearchDto>
}