package com.example.jetpackcomposetutorial.common.di

import android.content.Context
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Singleton
    @Provides
    fun providesUserRepository(@ApplicationContext context: Context): UserRepository {
        return UserRepository(context)
    }
}