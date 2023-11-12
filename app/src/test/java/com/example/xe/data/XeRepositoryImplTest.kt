package com.example.xe.data

import assertk.assertThat
import assertk.assertions.isTrue
import com.example.xe.utils.JSON_SAMPLE_FROM_XE
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class XeRepositoryImplTest {

    lateinit var xeRepositoryImpl: XeRepositoryImpl
    lateinit var mockWebServer: MockWebServer
    lateinit var xeApi: XeApi

    @BeforeEach
    fun setUp(){
        mockWebServer = MockWebServer()
        xeApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()

        xeRepositoryImpl = XeRepositoryImpl(xeApi)
    }

    @Test
    fun `Pass a sample JSON from XE, return list with SearchDto`() = runBlocking{
        mockWebServer.enqueue(
            MockResponse().setBody(JSON_SAMPLE_FROM_XE)
        )
        val result = xeRepositoryImpl.searchProducts("")

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Pass an HTTP client response code, result is failure`() = runBlocking{
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )
        val result = xeRepositoryImpl.searchProducts("")

        assertThat(result.isFailure).isTrue()

    }

}