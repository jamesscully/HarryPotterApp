package com.example.harrypottercharacters.data.daos

import androidx.room.*
import com.example.harrypottercharacters.models.Character

@Dao
interface CharacterDao {
    @Insert
    fun insert(vararg character : Character)

    @Delete
    fun delete(vararg character: Character)

    @Update
    fun update(character: Character)


}