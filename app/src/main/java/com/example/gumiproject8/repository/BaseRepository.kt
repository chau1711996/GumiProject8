package com.example.gumiproject8.repository

import com.example.gumiproject8.data.ApiService

class BaseRepository(private val apiService: ApiService){
    suspend fun getMyData(name: String) = apiService.getMyData(name)
    suspend fun getMyDataDetail(name: String, id: Int) = apiService.getMyDataDetail(name, id)
    suspend fun getMyListDataDetail(name: String, id: Int, nameOfList: String) = apiService.getMyListDataDetail(name, id, nameOfList)
}