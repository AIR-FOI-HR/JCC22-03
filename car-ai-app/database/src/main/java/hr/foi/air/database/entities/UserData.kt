package hr.foi.air.database.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(
    val username: String = "",
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val surname: String = "",
) {
    val isUserSignedIn: Boolean
        get() = username.isNotBlank() && password.isNotBlank()

    val isUserRegistered: Boolean
        get() = isUserSignedIn && email.isNotBlank() && name.isNotBlank() && surname.isNotBlank()
}
