package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository

import androidx.paging.PagingData
import com.example.jetpackcomposetutorial.common.data.model.UserData
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    fun fetchUserData(): Flow<List<UserData>>

    fun fetchUserDataWithPagination(): Flow<PagingData<UserData>>
}