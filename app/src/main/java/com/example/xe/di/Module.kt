package com.example.xe.di

import android.app.Application
import com.example.xe.data.XeApi
import com.example.xe.data.XeRepositoryImpl
import com.example.xe.domain.XeRepository
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.utils.BASE_URL
import com.example.xe.utils.hasInternet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesOkHttp(
        app : Application
    ) : OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(app.applicationContext.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasInternet(app.applicationContext))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }



    @Provides
    @Singleton
    fun providesXeApi(
        client : OkHttpClient
    ) : XeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesXeRepository(api: XeApi) : XeRepository = XeRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesFilterDigits() = FilterDigits()




}