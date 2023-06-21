package hr.foi.air.caraiapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.database.FirebaseRepository
import hr.foi.air.database.entities.LogsFeed


class LogsFeedViewModel : ViewModel() {

    private val _logsFeedLiveData = MutableLiveData<List<LogsFeed>>()
    val logsFeedLiveData: LiveData<List<LogsFeed>> = _logsFeedLiveData

    fun fetchLogsFeedByCarId(carId: String) {
        FirebaseRepository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }
}
