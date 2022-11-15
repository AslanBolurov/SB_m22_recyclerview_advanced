package com.skillbox.aslanbolurov.rickandmorty.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseModel(
    @Json(name = "info")
    val info: Info?,
    @Json(name="results")
    val results: List<Results>
)
