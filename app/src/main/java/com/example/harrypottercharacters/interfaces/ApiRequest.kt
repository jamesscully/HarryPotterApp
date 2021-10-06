package com.example.harrypottercharacters.interfaces

import com.example.harrypottercharacters.models.Character
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("characters")
    fun getAllCharacters() : Call<List<Character>>
}