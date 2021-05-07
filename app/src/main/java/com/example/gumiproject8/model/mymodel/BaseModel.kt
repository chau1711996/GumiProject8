package com.example.gumiproject8.model.mymodel

import androidx.room.PrimaryKey
import com.example.gumiproject8.model.Thumbnail
import com.example.gumiproject8.utils.Constants
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class BaseModel(
    val id: Int,
    val title: String?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail,
): Serializable{
    val myName: String = if(title.isNullOrEmpty()) name.toString() else title
    val myDes: String = if(description.isNullOrEmpty()) "No Description for ${myName}" else description
    val myListDetail: String = if(title.isNullOrEmpty()) Constants.CHARACTER_DETAIL_LIST else Constants.COMIC_DETAIL_LIST
    val myImage: String = "${thumbnail.path}/portrait_incredible.${thumbnail.extension}"
}
