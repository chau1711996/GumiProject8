package com.example.gumiproject8.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.favorite.FavoriteListDetail

@Database(entities = [FavoriteEntity::class, FavoriteListDetail::class], version = 11, exportSchema = false)
abstract class BaseDatabase: RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO
}