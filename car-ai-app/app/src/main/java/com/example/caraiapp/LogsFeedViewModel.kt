package com.example.caraiapp


import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.caraiapp.entities.Logs

class LogsFeedViewModel(
    private val myRepository: DAO,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _logsFeedLiveData = MutableLiveData<List<Logs>>()
    val logsFeedLiveData: LiveData<List<Logs>> = _logsFeedLiveData
    fun fetchLogsFeedByCarId(carId: String){
        myRepository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).repository
                LogsFeedViewModel(
                    myRepository = myRepository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}