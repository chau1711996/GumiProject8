package com.example.gumiproject8.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.favorite.FavoriteListDetail
import com.example.gumiproject8.model.mymodel.BaseModel

@Dao
interface FavoriteDAO {
    @Insert
    suspend fun insert(favorite: FavoriteEntity)

    @Insert
    suspend fun insertListDetail(list: FavoriteListDetail)

    @Query("select*from favorite_table")
    suspend fun getAllFavorite(): MutableList<FavoriteEntity>

    @Query("select*from favorite_table where title = :title")
    suspend fun getAllFavoriteByName(title: String): MutableList<FavoriteEntity>

    @Query("delete from favorite_table where idProduct = :id")
    suspend fun delete(id: Int)

    @Query("select*from favorite_table where idProduct = :id")
    suspend fun getFavoriteById(id: Int): MutableList<FavoriteEntity>

    @Query("select*from favorite_detail_table where idProduct = :id")
    suspend fun getListDetailFavoriteById(id: Int): MutableList<FavoriteListDetail>
}