package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.operations

import com.example.jetpackcomposetutorial.common.data.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface UserDataInterface {
    @GET("/v3/268ea75c-7fe8-4344-b07f-e6c9c2d7744e")
    suspend fun getUserList(): Response<List<UserData>>
}