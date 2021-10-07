package com.example.harrypottercharacters.data

import com.example.harrypottercharacters.Constants
import com.example.harrypottercharacters.interfaces.ApiRequests
import com.example.harrypottercharacters.json.CharacterDeserializer
import com.example.harrypottercharacters.models.Character
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// technically not a Service, but still provides a service!
object ApiService {

    private val gson: Gson = GsonBuilder().registerTypeAdapter(Character::class.java, CharacterDeserializer()).create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val api: ApiRequests = retrofit.create(ApiRequests::class.java)

    suspend fun getAllCharacters() = api.getAllCharacters()
    suspend fun getAllStaff() = api.getAllStaff()
    suspend fun getAllStudents() = api.getAllStudents()
    suspend fun getAllHouse(house: String) = api.getByHouse(house)

}