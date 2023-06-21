package hr.foi.air.database

import androidx.lifecycle.MutableLiveData
import hr.foi.air.database.entities.Car
import hr.foi.air.database.entities.CarOwner
import hr.foi.air.database.entities.LogsFeed

interface DAO {

    fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<LogsFeed>>, carId: String)

    fun fetchCarOwnersByUsername(liveData: MutableLiveData<List<CarOwner>>, username: String)

    fun fetchCarById(carId: String, onSuccess: (Car) -> Unit)
}
