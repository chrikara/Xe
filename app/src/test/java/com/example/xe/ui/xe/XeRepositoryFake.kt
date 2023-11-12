package com.example.xe.ui.xe

import com.example.xe.domain.XeRepository
import com.example.xe.domain.model.SearchDto

class XeRepositoryFake : XeRepository {

    val searchItems = listOf(
        SearchDto(
            mainText = "Nafplio",
            placeId = "123cekjeoe123:1",
            secondaryText = "Ναύπλιο"
        ),
        SearchDto(
            mainText = "Athens",
            placeId = "123cekjeoe1ασδασδ23:1",
            secondaryText = "Αθήνα"
        ),
    )
    val hasError = false
    override suspend fun searchProducts(query: String): Result<List<SearchDto>> {

        return if(hasError){
            Result.failure(Throwable())
        }else{
            Result.success(searchItems)
        }


    }
}