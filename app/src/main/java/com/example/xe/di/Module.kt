package com.example.xe.di

import com.example.xe.domain.usecase.FilterDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesFilterDigits() = FilterDigits()
}