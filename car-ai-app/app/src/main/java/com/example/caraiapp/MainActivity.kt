package com.example.caraiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private var firebaseDataBase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseDataBase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDataBase?.getReference("users")

        getData()
    }

    private fun getData() {
        databaseReference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("Data changed", "onDataChange:$snapshot ")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", "onCancelled:${error.toException()} ")
            }

        })
    }
}