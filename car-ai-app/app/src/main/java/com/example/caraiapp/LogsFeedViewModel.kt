package com.example.caraiapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caraiapp.entities.Logs

class LogsFeedViewModel: ViewModel() {

    private val repository = Repository()
    private val _logsFeedLiveData = MutableLiveData<List<Logs>>()
    val logsFeedLiveData: LiveData<List<Logs>> = _logsFeedLiveData

    fun fetchLogsFeed(){
        repository.fetchLogsFeed(_logsFeedLiveData)
    }


}