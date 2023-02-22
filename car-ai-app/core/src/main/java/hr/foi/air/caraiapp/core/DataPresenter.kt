package hr.foi.air.caraiapp.core

import android.content.Context
import androidx.fragment.app.Fragment
import android.graphics.drawable.Drawable

//ListLogFeedPresenter module
interface DataPresenter {
    fun getIcon(context: Context) : Drawable
    fun getName(context: Context) : String
    fun getFragment() : Fragment
    //fun setData(logFeedItems: List<Logs>?)
    //fun setData() add if needed
}