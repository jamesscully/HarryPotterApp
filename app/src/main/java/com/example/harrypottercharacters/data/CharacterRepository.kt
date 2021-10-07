package com.example.harrypottercharacters.data

import com.example.harrypottercharacters.data.daos.CharacterDao

// charDao is used to interface with the Room database, providing second source of data
class CharacterRepository(private val charDao : CharacterDao) {

    suspend fun getAllCharacters() = ApiService.getAllCharacters()
    suspend fun getAllStaffResponse() = ApiService.getAllStaff()
    suspend fun getAllStudentsResponse() = ApiService.getAllStudents()
    suspend fun getAllByHouseResponse(house : String) = ApiService.getAllHouse(house)
}