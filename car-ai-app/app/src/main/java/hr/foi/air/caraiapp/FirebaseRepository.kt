package hr.foi.air.caraiapp

import androidx.lifecycle.MutableLiveData
import com.example.database.DAO
import com.example.database.entities.Logs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepository : DAO {

    private val database = Firebase.database
    private val logFeedReference = database.getReference("logs")

    override fun fetchLogsFeedByCarId(liveData: MutableLiveData<List<Logs>>, carId: String) {
        logFeedReference
            .orderByChild("carId")
            .equalTo(carId)
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val logFeedItem: List<Logs> = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Logs::class.java)!!
                }
                //logFeedItem.sortedByDescending { it.time }
                //Log.i("Logs", logFeedItem.toString())
                liveData.postValue(logFeedItem)

            }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }
}