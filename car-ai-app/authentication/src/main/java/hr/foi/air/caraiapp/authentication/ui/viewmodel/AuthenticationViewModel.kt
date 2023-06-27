package hr.foi.air.caraiapp.authentication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foi.air.database.FirebaseRepository
import hr.foi.air.database.entities.UserData

class AuthenticationViewModel : ViewModel() {

    private val _userDataLiveData = MutableLiveData<UserData?>()
    val userDataLiveData: LiveData<UserData?> = _userDataLiveData

    private val _registrationFailed = MutableLiveData<Boolean>()
    val registrationFailed: LiveData<Boolean> = _registrationFailed

    fun onSignInTapped(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) return

        FirebaseRepository.signInUser(
            username = username,
            password = password,
            onSuccess = { user ->
                _userDataLiveData.value = UserData(
                    username = username,
                    email = user.email,
                    name = user.name,
                    password = user.password,
                    surname = user.surname,
                )
            },
            onFailure = { userExists ->
                _userDataLiveData.value = if (userExists) {
                    null
                } else {
                    UserData(username = username, password = password)
                }
            },
        )
    }

    fun onCompleteRegistration(firstName: String, lastName: String, email: String) {
        val userData = _userDataLiveData.value ?: return
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || !userData.isUserSignedIn) return

        val newUserData = userData.copy(name = firstName, surname = lastName, email = email)

        FirebaseRepository.registerUser(
            userData = newUserData,
            onSuccess = { _userDataLiveData.value = newUserData },
            onFailure = { _registrationFailed.value = true },
        )
    }
}
