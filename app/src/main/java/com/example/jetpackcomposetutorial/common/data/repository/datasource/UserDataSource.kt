package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource

import com.example.jetpackcomposetutorial.common.data.model.UserData

interface UserDataSource {
    suspend fun getUserList(): List<UserData>

    suspend fun getUserList_Pagination(startId: Int, pageSize: Int): List<UserData>
}