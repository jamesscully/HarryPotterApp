package com.example.harrypottercharacters.data

import com.example.harrypottercharacters.data.daos.CharacterDao
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val charDao : CharacterDao) {

    val allCharacters : Flow<List<Character>> = charDao.getAll()

}