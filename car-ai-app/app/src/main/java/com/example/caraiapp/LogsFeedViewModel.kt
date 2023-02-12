package com.example.caraiapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.database.DAO
import com.example.database.entities.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogsFeedViewModel @Inject constructor(
    private val repository: DAO
    ): ViewModel() {
//class LogsFeedViewModel : ViewModel() {
    //private val repository = FirebaseDAO()
    private val _logsFeedLiveData = MutableLiveData<List<Logs>>()
    val logsFeedLiveData: LiveData<List<Logs>> = _logsFeedLiveData

    fun fetchLogsFeedByCarId(carId: String){
        repository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }

}