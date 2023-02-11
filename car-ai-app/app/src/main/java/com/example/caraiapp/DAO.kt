package com.example.caraiapp

import androidx.lifecycle.MutableLiveData
import com.example.caraiapp.entities.Logs

interface DAO {

    fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<Logs>>, carId: String)
}