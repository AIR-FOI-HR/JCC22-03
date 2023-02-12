package com.example.caraiapp.di

import android.app.Application
import com.example.caraiapp.repository.FirebaseRepository
import com.example.database.DAO
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    fun provideMyRepository(app: Application) : DAO {
        return FirebaseRepository(app)
    }
}