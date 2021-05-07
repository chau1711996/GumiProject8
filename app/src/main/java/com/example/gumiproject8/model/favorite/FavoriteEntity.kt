package com.example.gumiproject8.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gumiproject8.model.mymodel.BaseModel
import com.example.gumiproject8.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val idProduct: Int,
    val name: String,
    val title: String,
    val des: String,
    val image: String,
) : Serializable{
}