package hr.foi.air.database

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import hr.foi.air.database.entities.Car
import hr.foi.air.database.entities.CarOwner
import hr.foi.air.database.entities.LogsFeed

object FirebaseRepository : DAO {

    private val database = Firebase.database
    private val logFeedReference = database.reference.child("logs")
    private val carOwnersReference = database.reference.child("carOwners")
    private val carsReference = database.reference.child("cars")

    override fun fetchLogsFeedByCarId(
        liveData: MutableLiveData<List<LogsFeed>>,
        carId: String,
    ) {
        logFeedReference
            .orderByChild("carId")
            .equalTo(carId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val logFeedItem: List<LogsFeed> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(LogsFeed::class.java)!!
                    }
                    //logFeedItem.sortedByDescending { it.time }
                    //Log.i("Logs", logFeedItem.toString())
                    liveData.postValue(logFeedItem)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRepository", error.toString())
                }
            })
    }

    override fun fetchCarOwnersByUsername(
        liveData: MutableLiveData<List<CarOwner>>,
        username: String,
    ) {
        carOwnersReference
            .orderByChild("username")
            .equalTo(username)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val carOwners = snapshot.children.map { it.getValue(CarOwner::class.java)!! }
                    liveData.postValue(carOwners)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRepository", error.toString())
                }
            })
    }

    override fun fetchCarById(carId: String, onSuccess: (Car) -> Unit) {
        carsReference
            .child(carId)
            .get()
            .addOnSuccessListener {
            onSuccess(it.getValue(Car::class.java)!!)
        }.addOnFailureListener { it.printStackTrace() }
    }
}
