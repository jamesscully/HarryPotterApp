package com.example.harrypottercharacters.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert
    fun insert(vararg character : Character)

    @Delete
    fun delete(vararg character: Character)

    @Update
    fun update(character: Character)

    @Query("SELECT * FROM Character ORDER BY Name ASC")
    fun getAll() : Flow<List<Character>>

    @Query("SELECT * FROM Character WHERE id = :id")
    fun getById(id: Int) : Character

    @Query("SELECT * FROM Character WHERE name = :name ")
    fun getByName(name: String) : Character

//    @Query("SELECT * FROM Character WHERE house = :house ORDER BY name ASC")
//    suspend fun getByHouse(house : String) : Flow<List<Character>>

}