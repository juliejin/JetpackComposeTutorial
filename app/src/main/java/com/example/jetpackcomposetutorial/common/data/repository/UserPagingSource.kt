package com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposetutorial.com.example.jetpackcomposetutorial.common.data.repository.datasource.UserDataSource
import com.example.jetpackcomposetutorial.common.data.model.UserData
import kotlin.math.max

private const val STARTING_PAGE_INDEX = 0
const val USER_PAGING_SIZE = 4
class UserPagingSource(
    private val remoteDatasource: UserDataSource
): PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val currentKey = params.key ?: STARTING_PAGE_INDEX
            val userList = remoteDatasource.getUserList_Pagination(
                currentKey,
                params.loadSize
            )
            LoadResult.Page(
                data = userList,
                prevKey = if (currentKey == STARTING_PAGE_INDEX) null else ensureValidKey(currentKey - params.loadSize),
                nextKey = if (userList.isEmpty()) null else currentKey + params.loadSize + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX, key)
}