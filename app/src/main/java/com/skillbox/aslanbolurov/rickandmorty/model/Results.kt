package com.skillbox.aslanbolurov.rickandmorty.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(
    @Json(name="id")
    val id:Int?,
    @Json(name="name")
    val name:String?,
    @Json(name="status")
    val status:String?,
    @Json(name="species")
    val species:String?,
    @Json(name="type")
    val type:String?,
    @Json(name="gender")
    val gender:String?,
    @Json(name="imageUrl")
    val imageUrl:String?,
    @Json(name="location")
    val location:Location?
)