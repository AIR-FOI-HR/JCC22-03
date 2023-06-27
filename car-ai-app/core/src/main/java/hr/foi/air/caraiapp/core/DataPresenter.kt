package hr.foi.air.caraiapp.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

interface DataPresenter {
    fun getIcon(context: Context): Drawable
    fun getName(context: Context): String
    fun getFragment(): Fragment
}
