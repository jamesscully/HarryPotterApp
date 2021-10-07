package com.example.harrypottercharacters.viewmodels

import androidx.lifecycle.*
import com.example.harrypottercharacters.data.CharacterRepository
import com.example.harrypottercharacters.enums.FilterEnum
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.launch
class MainActivityViewModel(private val repo: CharacterRepository) : ViewModel() {

    val TAG = "MainActivityViewModel"

    private val _response = MutableLiveData<List<Character>>()
    val filter = MutableLiveData<FilterEnum>()

    // reflection for Mutable _response
    val characters : LiveData<List<Character>>
        get() = _response

    init {
        // load all characters by default
        getAllCharacters()
    }

    // Exposed method for setting our needed filter
    fun setFilter(filter : FilterEnum) {
        this.filter.value = filter
        handleFilter()
    }

    // Executes the API requests for each type of filter
    private fun handleFilter() {
        when(filter.value) {
            FilterEnum.Staff -> getAllStaff()
            FilterEnum.Student -> getAllStudents()
            FilterEnum.Gryffindor -> getByHouse("Gryffindor")
            FilterEnum.Ravenclaw -> getByHouse("Ravenclaw")
            FilterEnum.Hufflepuff -> getByHouse("Hufflepuff")
            FilterEnum.Slytherin -> getByHouse("Slytherin")
            else -> getAllCharacters()
        }
    }

    // Generic function to handle getting our data from repo
    private fun handleResponseData(func: suspend () -> List<Character>) = viewModelScope.launch {
        _response.postValue(func())
    }

    // routines to handle getting each type of data
    private fun getAllCharacters() = handleResponseData { repo.getAllCharacters() }
    private fun getAllStaff()      = handleResponseData { repo.getAllStaffResponse() }
    private fun getAllStudents()   = handleResponseData { repo.getAllStudentsResponse() }
    private fun getByHouse(house : String) = handleResponseData { repo.getAllByHouseResponse(house) }

    class MainActivityViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(repository) as T
            }
            throw IllegalArgumentException("")
        }
    }

}

