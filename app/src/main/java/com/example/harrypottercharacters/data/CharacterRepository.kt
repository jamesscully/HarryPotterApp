package com.example.harrypottercharacters.data

import android.util.Log
import com.example.harrypottercharacters.data.daos.CharacterDao
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.coroutineScope
import retrofit2.Response

// charDao is used to interface with the Room database, providing second source of data
class CharacterRepository(private val charDao : CharacterDao) {

    val TAG = "CharacterRepository"

    // Generic function for handling Response<> calls from the API
    // Returns a list (potentially empty) and the response code given - to check for errors
    suspend fun handleResponse(response: Response<List<Character>>) : Pair<List<Character>, Int> {
        var list = emptyList<Character>()

        coroutineScope {
            if(response.isSuccessful || response.body() != null) {
                list = response.body()!!
            } else {
                Log.e(TAG, "Error parsing response, code: ${response.code()}")
            }
        }

        return Pair(list, response.code())
    }

    suspend fun getAllCharacters() : List<Character> {
        val response = ApiService.getAllCharacters()
        val result = handleResponse(response)
        val list : List<Character> = handleResponse(response).first
        val returnCode : Int = result.second

        if(returnCode == 404) {
            // fetch from database instead, etc
        }

        return list
    }
    suspend fun getAllStaffResponse() : List<Character> {
        val response = ApiService.getAllStaff()

        return handleResponse(response).first
    }
    suspend fun getAllStudentsResponse() : List<Character> {
        val response = ApiService.getAllStudents()

        return handleResponse(response).first
    }
    suspend fun getAllByHouseResponse(house : String) : List<Character> {
        val response = ApiService.getAllHouse(house)

        return handleResponse(response).first
    }
}