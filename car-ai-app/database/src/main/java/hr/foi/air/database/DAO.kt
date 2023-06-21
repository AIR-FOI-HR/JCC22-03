package hr.foi.air.database

import androidx.lifecycle.MutableLiveData
import hr.foi.air.database.entities.LogsFeed

interface DAO {

    fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<LogsFeed>>, carId: String)
}