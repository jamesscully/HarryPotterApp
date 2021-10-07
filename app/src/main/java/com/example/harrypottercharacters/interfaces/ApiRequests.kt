package com.example.harrypottercharacters.interfaces

import com.example.harrypottercharacters.models.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {
    @GET("characters")
    suspend fun getAllCharacters() : Response<List<Character>>

    @GET("characters/students")
    suspend fun getAllStudents() : Response<List<Character>>

    @GET("characters/staff")
    suspend fun getAllStaff() : Response<List<Character>>

    @GET("characters/house/{house}")
    suspend fun getByHouse(@Path("house") house : String) : Response<List<Character>>
}