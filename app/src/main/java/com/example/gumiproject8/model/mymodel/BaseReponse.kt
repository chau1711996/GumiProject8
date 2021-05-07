package com.example.gumiproject8.model.mymodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BaseReponse(
    @Json(name = "data")
    val data: BaseData
)