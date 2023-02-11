package com.example.caraiapp

import android.app.Application

class MyApplication : Application() {
    val repository = FirebaseDAO()
}