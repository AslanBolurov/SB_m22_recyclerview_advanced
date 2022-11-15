package com.skillbox.aslanbolurov.rickandmorty.data

import android.util.Log
import com.skillbox.aslanbolurov.rickandmorty.model.ResponseModel
import com.skillbox.aslanbolurov.rickandmorty.model.Results
import kotlinx.coroutines.delay

class RickymortyRepository {

    suspend fun getAllCharacters(): List<Results> {
        Log.d("aslan555", "in")
        return RetrofitInstance.rickyMortiApi.getCharacters().results
    }

    suspend fun getCharactersByPage(page:Int):List<Results>{
        delay(2000)
        return RetrofitInstance.rickyMortiApi.getCharactersByPage(page).results
    }

}