package com.example.xe.data

import com.example.xe.domain.XeRepository
import com.example.xe.domain.model.SearchDto
import kotlin.coroutines.cancellation.CancellationException

class XeRepositoryImpl(
    private val api: XeApi
) : XeRepository {


        override suspend fun searchProducts(query: String): Result<List<SearchDto>> {
            return try {
                val listSearchDto = api.getData(query)

                Result.success(listSearchDto)
            }catch (e: Exception){
                if(e is CancellationException) throw e
                Result.failure(e)
            }
        }

    }
