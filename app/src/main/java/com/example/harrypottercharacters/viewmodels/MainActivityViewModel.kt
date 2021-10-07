package com.example.harrypottercharacters.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.models.Character
import org.w3c.dom.CharacterData
import kotlin.coroutines.coroutineContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var characters = MutableLiveData<List<Character>>()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        val context = getApplication<Application>().applicationContext
        val db = CharacterDatabase.getInstance(context)

        characters.value = db.DAO.getAll()
    }
}