package com.example.gumiproject8.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gumiproject8.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.FAVORITE_DETAIL_TABLE_NAME)
data class FavoriteListDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idProduct: Int,
    val name: String,
    val image: String,
) : Serializable {
}