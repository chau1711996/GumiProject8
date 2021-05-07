package com.example.gumiproject8.repository

import com.example.gumiproject8.data.room.FavoriteDAO
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.favorite.FavoriteListDetail
import com.example.gumiproject8.model.mymodel.BaseModel

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) = favoriteDAO.insert(favoriteEntity)
    suspend fun deleteFavorite(id: Int) = favoriteDAO.delete(id)
    suspend fun getFavoriteById(id: Int) = favoriteDAO.getFavoriteById(id)
    suspend fun getFavoriteByName(title: String) = favoriteDAO.getAllFavoriteByName(title)
    suspend fun insertFavoriteListDetail(list: FavoriteListDetail) = favoriteDAO.insertListDetail(list)
    suspend fun getListDetailFavoriteById(id: Int) = favoriteDAO.getListDetailFavoriteById(id)
}