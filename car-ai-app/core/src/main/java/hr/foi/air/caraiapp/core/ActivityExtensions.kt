package hr.foi.air.caraiapp.core

import android.app.Activity
import android.content.Context
import androidx.core.content.edit

fun Activity.getLoggedInUser(): String? =
    getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        .getString(Constants.KEY_USERNAME, null)

fun Activity.saveUser(username: String?) {
    getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit {
        putString(Constants.KEY_USERNAME, username)
        apply()
    }
}
