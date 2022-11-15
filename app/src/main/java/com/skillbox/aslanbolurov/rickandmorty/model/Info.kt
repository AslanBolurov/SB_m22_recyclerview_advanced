package com.skillbox.aslanbolurov.rickandmorty.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name="count")
    val count:Int?,
    @Json(name="pages")
    val pages:Int?
)