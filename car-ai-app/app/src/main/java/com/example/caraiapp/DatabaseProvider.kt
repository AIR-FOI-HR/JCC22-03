package com.example.caraiapp

import com.example.database.DAO

class DatabaseProvider {

    fun provideDatabase() : DAO
    {
        return FirebaseRepository()
    }
}