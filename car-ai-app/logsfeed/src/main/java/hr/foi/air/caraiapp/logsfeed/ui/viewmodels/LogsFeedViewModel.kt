package hr.foi.air.caraiapp.logsfeed.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.caraiapp.logsfeed.models.Car
import hr.foi.air.database.FirebaseRepository
import hr.foi.air.database.entities.CarOwner
import hr.foi.air.database.entities.LogsFeed

class LogsFeedViewModel : ViewModel() {

    private val _logsFeedLiveData = MutableLiveData<List<LogsFeed>>()
    val logsFeedLiveData: LiveData<List<LogsFeed>> = _logsFeedLiveData

    private val _carOwnersLiveData = MutableLiveData<List<CarOwner>>()
    val carOwnersLiveData: LiveData<List<CarOwner>> = _carOwnersLiveData

    private val _carsLiveData = MutableLiveData<List<Car>>()
    val carsLiveData: LiveData<List<Car>> = _carsLiveData

    fun fetchCarOwners(username: String) {
        FirebaseRepository.fetchCarOwnersByUsername(
            liveData = _carOwnersLiveData,
            username = username,
        )
    }

    fun fetchCars(carOwners: List<CarOwner>) {
        val cars = mutableListOf<Car>()
        carOwners.forEachIndexed { index, carOwner ->
            FirebaseRepository.fetchCarById(carId = carOwner.carId) { carEntity ->
                cars.add(Car(id = carOwner.carId, name = carEntity.carName))
                if (index == carOwners.size - 1) {
                    _carsLiveData.postValue(cars)
                }
            }
        }
    }

    fun onCarSelected(carName: String) {
        _carsLiveData.value?.let {
            val car = it.firstOrNull() { it.name == carName } ?: return@let
            fetchLogsFeedByCarId(carId = car.id)
        }
    }

    private fun fetchLogsFeedByCarId(carId: String) {
        FirebaseRepository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }
}
