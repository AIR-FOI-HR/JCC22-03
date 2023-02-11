package com.example.caraiapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.database.DAO
import com.example.database.entities.Logs


class LogsFeedViewModel(private val repository: DAO) {

    private val _logsFeedLiveData = MutableLiveData<List<Logs>>()
    val logsFeedLiveData: LiveData<List<Logs>> = _logsFeedLiveData

    fun fetchLogsFeedByCarId(carId: String){
        repository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }

}