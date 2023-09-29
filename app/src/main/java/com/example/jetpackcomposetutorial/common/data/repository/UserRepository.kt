package com.example.jetpackcomposetutorial.common.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.IUserRepository
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.USER_PAGING_SIZE
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.UserPagingSource
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.UserDataSource
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.example.jetpackcomposetutorial.common.di.UserRemoteDatasource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class UserRepository @Inject constructor(private val context: Context, @UserRemoteDatasource val dataSource: UserDataSource): IUserRepository {
    override fun fetchUserData() = flow {
        val result = dataSource.getUserList()
        emit(result)
    }

    override fun fetchUserDataWithPagination(): Flow<PagingData<UserData>> {
        return Pager<Int, UserData>(
            config = PagingConfig(USER_PAGING_SIZE),
            pagingSourceFactory =  {
                UserPagingSource(dataSource)
            }
        ).flow
    }

    fun fetchUserDataFlow() = callbackFlow {
        fetchUserDataAsync {
            trySend(it)
        }
        awaitClose {}
    }

    fun fetchUserDataAsync(callback: (List<UserData>)->Unit) {
        val result = parseData()
        callback.invoke(result)
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