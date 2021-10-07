package com.example.harrypottercharacters.viewmodels

import androidx.lifecycle.*
import com.example.harrypottercharacters.data.CharacterRepository
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repo: CharacterRepository) : ViewModel() {

    val TAG = "MainActivityViewModel"
    val _response = MutableLiveData<List<Character>>()
    val characters : LiveData<List<Character>>
        get() = _response

    init {
        // load all characters by default
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch {
        repo.getAllCharacters().let { response ->
            if(response.isSuccessful) {
                _response.postValue(response.body())
            }
        }
    }

    class MainActivityViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(repository) as T
            }
            throw IllegalArgumentException("")
        }
    }

}