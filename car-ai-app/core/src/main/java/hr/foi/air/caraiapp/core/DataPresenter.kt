package hr.foi.air.caraiapp.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import hr.foi.air.database.DAO
import hr.foi.air.database.entities.Car
import hr.foi.air.database.entities.CarOwner
import hr.foi.air.database.entities.LogsFeed
//sort the imports
interface DataPresenter {
    fun getIcon(context: Context): Drawable
    fun getName(context: Context): String
    fun getFragment(): Fragment
    fun setData(logsFeedData : LiveData<List<LogsFeed>>, carOwnersLiveData: LiveData<List<CarOwner>>, carsLiveData: LiveData<List<Car>> )
    fun setRepository(repository : DAO)
}
