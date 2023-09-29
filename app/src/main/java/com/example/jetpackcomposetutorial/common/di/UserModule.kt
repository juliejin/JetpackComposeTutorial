package com.example.jetpackcomposetutorial.common.di

import android.content.Context
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.IUserRepository
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.UserDataSource
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.remote.UserRemoteDataSource
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class UserRemoteDatasource
@InstallIn(SingletonComponent::class)
@Module
abstract class UserModule {
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

    @Binds
    @UserRemoteDatasource
    abstract fun bindRemoteDataSource(remoteDataSource: UserRemoteDataSource): UserDataSource
}