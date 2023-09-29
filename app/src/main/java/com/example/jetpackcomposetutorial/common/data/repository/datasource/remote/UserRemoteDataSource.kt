package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.remote

import android.content.Context
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.UserDataSource
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.operations.UserDataOperation
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val context: Context): UserDataSource {
    override suspend fun getUserList(): List<UserData> {
        val result = UserDataOperation().retrofitCall()
        val operationResult = if (result.isSuccessful) result.body() ?: listOf() else listOf()
        return operationResult
    }

    override suspend fun getUserList_Pagination(startId: Int, pageSize: Int): List<UserData> {
        val data = parseData()
        delay(1000)
        return if (startId < data.size) {
            data.subList(startId, minOf(data.size, startId + pageSize))
        } else listOf()
    }

    fun parseData(): List<UserData> {
        var jsonStr = ""
        try {
            jsonStr = context.assets
                .open("user-data.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        var userData = listOf<UserData>()
        try {
            val listType = object : TypeToken<List<UserData>>(){}.type
            userData = Gson().fromJson(jsonStr, listType)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return userData
    }

}