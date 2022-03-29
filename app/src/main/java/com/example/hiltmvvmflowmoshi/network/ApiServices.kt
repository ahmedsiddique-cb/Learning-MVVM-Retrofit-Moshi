package com.example.hiltmvvmflowmoshi.network

import com.example.hiltmvvmflowmoshi.data.Dogs
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    companion object {
        val BASE_URL = "https://api.thedogapi.com/"
    }

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Dogs>
}