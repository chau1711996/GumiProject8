package com.example.gumiproject8.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.gumiproject8.model.mymodel.BaseReponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Dao
interface ApiService {

    @GET("v1/public/{name}")
    suspend fun getMyData(@Path("name") name: String): Response<BaseReponse>

    @GET("v1/public/{name}/{id}")
    suspend fun getMyDataDetail(
        @Path("name") name: String,
        @Path("id") id: Int
    ): Response<BaseReponse>
    @GET("v1/public/{name}/{id}/{nameOfList}")
    suspend fun getMyListDataDetail(
        @Path("name") name: String,
        @Path("id") id: Int,
        @Path("nameOfList") nameOfList: String
    ): Response<BaseReponse>
}