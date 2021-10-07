package com.example.harrypottercharacters

import android.app.Application
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.data.CharacterRepository
import org.w3c.dom.CharacterData

class App : Application() {
    // use Application context to init these, rather than any activity
    val database by lazy { CharacterDatabase.getInstance(this) }
    val repo by lazy { CharacterRepository(database.DAO) }
}