package com.example.harrypottercharacters.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.data.CharacterRepository
import com.example.harrypottercharacters.models.Character

class MainActivityViewModel(private val repo: CharacterRepository) : ViewModel() {

    val TAG = "MainActivityViewModel"

    val characters : LiveData<List<Character>> = repo.allCharacters.asLiveData()

    class MainActivityViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(repository) as T
            }
            throw IllegalArgumentException("")
        }
    }

}