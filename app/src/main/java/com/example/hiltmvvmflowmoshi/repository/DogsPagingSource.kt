package com.example.hiltmvvmflowmoshi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hiltmvvmflowmoshi.data.Dogs
import com.example.hiltmvvmflowmoshi.network.ApiServices
import retrofit2.HttpException
import java.io.IOException

class DogsPagingSource constructor(private val apiServices: ApiServices) : PagingSource<Int, Dogs>() {
    override fun getRefreshKey(state: PagingState<Int, Dogs>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dogs> {
        val page = params.key ?: 1
        return try {
            val response = apiServices.getAllDogs(page = page, limit = params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}