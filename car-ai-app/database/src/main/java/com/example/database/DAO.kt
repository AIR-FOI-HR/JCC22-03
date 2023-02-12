package com.example.database

import androidx.lifecycle.MutableLiveData
import com.example.database.entities.Logs

interface DAO {

    fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<Logs>>, carId: String)
}