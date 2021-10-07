package com.example.harrypottercharacters.viewmodels

import androidx.lifecycle.*
import com.example.harrypottercharacters.data.CharacterRepository
import com.example.harrypottercharacters.enums.FilterEnum
import com.example.harrypottercharacters.models.Character
import kotlinx.coroutines.launch
class MainActivityViewModel(private val repo: CharacterRepository) : ViewModel() {

    val TAG = "MainActivityViewModel"

    private val _response = MutableLiveData<List<Character>>()
    private val _filter = MutableLiveData<FilterEnum>()

    val characters : LiveData<List<Character>>
        get() = _response

    init {
        // load all characters by default
        getAllCharacters()
    }

    // Exposed method for setting our needed filter
    fun setFilter(filter : FilterEnum) {
        _filter.value = filter
        handleFilter()
    }

    // Executes the API requests for each type of filter
    private fun handleFilter() {
        when(_filter.value) {
            FilterEnum.STAFF -> getAllStaff()
            FilterEnum.STUDENT -> getAllStudents()
            FilterEnum.GRYFFINDOR -> getByHouse("Gryffindor")
            FilterEnum.RAVENCLAW -> getByHouse("Ravenclaw")
            FilterEnum.HUFFLEPUFF -> getByHouse("Hufflepuff")
            FilterEnum.SLYTHERIN -> getByHouse("Slytherin")
            else -> getAllCharacters()
        }
    }

    // routines to handle getting each type of data
    private fun getAllCharacters()          = viewModelScope.launch { _response.postValue(repo.getAllCharacters()) }
    private fun getAllStaff()               = viewModelScope.launch { _response.postValue(repo.getAllStaffResponse()) }
    private fun getAllStudents()            = viewModelScope.launch { _response.postValue(repo.getAllStudentsResponse()) }
    private fun getByHouse(house : String)  = viewModelScope.launch { _response.postValue(repo.getAllByHouseResponse(house)) }

    class MainActivityViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(repository) as T
            }
            throw IllegalArgumentException("")
        }
    }

}

