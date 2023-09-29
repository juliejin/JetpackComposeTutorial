package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context
}