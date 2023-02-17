package hr.foi.air.database

import androidx.lifecycle.MutableLiveData
import hr.foi.air.database.entities.Logs

interface DAO {

    fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<Logs>>, carId: String)
}