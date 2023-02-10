package com.example.caraiapp

import androidx.lifecycle.MutableLiveData
import com.example.caraiapp.entities.Logs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repository {

    private val database = Firebase.database
    private val carId = "car_id_1" //TODO: implement function to pass owned carId on login
    private val logFeedReference = database.getReference("logs")

    fun fetchLogsFeed(liveData: MutableLiveData<List<Logs>>) {
        logFeedReference
            .orderByChild("carId")
            .equalTo(carId)
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val logFeedItem: List<Logs> = snapshot.children.map {dataSnapshot ->
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