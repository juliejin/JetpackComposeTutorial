package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.operations

import com.example.jetpackcomposetutorial.common.data.model.UserData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UserDataOperation {
    //https://run.mocky.io/v3/268ea75c-7fe8-4344-b07f-e6c9c2d7744e
    suspend fun retrofitCall(): Response<List<UserData>> {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .client(okhttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserDataInterface::class.java)
        return service.getUserList()
    }

}