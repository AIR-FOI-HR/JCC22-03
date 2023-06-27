package hr.foi.air.caraiapp.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import hr.foi.air.authentication.R
import hr.foi.air.authentication.databinding.ActivityAuthenticationBinding
import hr.foi.air.caraiapp.authentication.ui.viewmodel.AuthenticationViewModel
import hr.foi.air.caraiapp.core.getLoggedInUser
import hr.foi.air.caraiapp.core.saveUser
import hr.foi.air.caraiapp.logsfeed.ui.LogsFeedActivity

class AuthenticationActivity : AppCompatActivity() {

    private val viewModel: AuthenticationViewModel by viewModels()

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getLoggedInUser()?.let { return startLogsActivity() }
        super.onCreate(savedInstanceState)

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListeners()
        setupObservers()
    }

    override fun onBackPressed() {
        binding.apply {
            if (registerGroup.isVisible) {
                registerGroup.isVisible = false
                signInGroup.isVisible = true
                title.text = getText(R.string.title_sign_in)
                submitButton.text = getText(R.string.action_sign_in)
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun setupListeners() = with(binding) {
        submitButton.setOnClickListener {
            when {
                signInGroup.isVisible && !registerGroup.isVisible -> {
                    viewModel.onSignInTapped(
                        username = username.text.toString(),
                        password = password.text.toString(),
                    )
                }
                !signInGroup.isVisible && registerGroup.isVisible -> {
                    viewModel.onCompleteRegistration(
                        firstName = lastName.text.toString(),
                        lastName = firstName.text.toString(),
                        email = email.text.toString(),
                    )
                }
                else -> {} // do-nothing
            }
        }
    }

    private fun setupObservers() = with(viewModel) {
        userDataLiveData.observe(this@AuthenticationActivity) { userData ->
            userData ?: return@observe showToast(textResId = R.string.toast_sign_in_failed)

            if (userData.isUserRegistered) {
                saveUser(username = userData.username)
                showToast(textResId = R.string.toast_welcome)
                startLogsActivity()
            } else {
                binding.apply {
                    signInGroup.isVisible = false
                    registerGroup.isVisible = true
                    title.text = getText(R.string.title_complete_registration)
                    submitButton.text = getText(R.string.action_submit)
                }
                showToast(textResId = R.string.toast_complete_registration, duration = Toast.LENGTH_LONG)
            }
        }
        registrationFailed.observe(this@AuthenticationActivity) { registrationFailed ->
            if (registrationFailed) {
                showToast(textResId = R.string.toast_registration_failed)
            }
        }
    }

    private fun showToast(@StringRes textResId: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, getText(textResId), duration).show()
    }

    private fun startLogsActivity() {
        startActivity(Intent(this, LogsFeedActivity::class.java))
        finish()
    }
}
