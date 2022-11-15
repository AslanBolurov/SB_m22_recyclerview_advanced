package com.skillbox.aslanbolurov.rickandmorty.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location (
    @Json(name="name")
    val name:String?
)
