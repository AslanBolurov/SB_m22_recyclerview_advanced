package com.skillbox.aslanbolurov.rickandmorty.data

import com.skillbox.aslanbolurov.rickandmorty.model.ResponseModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com"

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rickyMortiApi = retrofit.create(RickyMortiApi::class.java)
}

interface RickyMortiApi {
    @GET("/api/character")
    suspend fun getCharacters(): ResponseModel

    @GET("/api/character/?")
    suspend fun getCharactersByPage(
        @Query("page") pageNumber: Int
    ): ResponseModel
}