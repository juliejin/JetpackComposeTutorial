package com.example.jetpackcomposetutorial.common.data.repository

import android.content.Context
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow

class UserRepository(private val context: Context) {
    fun fetchUserData() = flow {
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

        emit(userData)
    }
}