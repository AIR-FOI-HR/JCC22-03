package hr.foi.air.caraiapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.foi.air.database.DAO
import hr.foi.air.database.entities.Logs


class LogsFeedViewModel(private val repository: DAO) {
//class LogsFeedViewModel : ViewModel() {
    //private val repository = FirebaseDAO()
    private val _logsFeedLiveData = MutableLiveData<List<Logs>>()
    val logsFeedLiveData: LiveData<List<Logs>> = _logsFeedLiveData

    fun fetchLogsFeedByCarId(carId: String){
        repository.fetchLogsFeedByCarId(_logsFeedLiveData, carId)
    }

}