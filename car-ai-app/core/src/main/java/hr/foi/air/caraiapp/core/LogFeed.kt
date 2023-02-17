package hr.foi.air.caraiapp.core

import androidx.fragment.app.Fragment

//ListLogFeed module
interface LogFeed {
    fun getFragment() : Fragment
    fun getName() : String
}