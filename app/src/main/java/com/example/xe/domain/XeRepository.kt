package com.example.xe.domain

import com.example.xe.domain.model.SearchDto
import retrofit2.http.Query

interface XeRepository {

    suspend fun searchProducts(query : String): Result<List<SearchDto>>

}