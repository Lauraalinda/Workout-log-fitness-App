package dev.alinda.myfitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.alinda.myfitnessapp.api.ApiClient
import dev.alinda.myfitnessapp.api.ApiInterface
import dev.alinda.myfitnessapp.databinding.ActivitySignupBinding
import dev.alinda.myfitnessapp.models.LoginRequest
import dev.alinda.myfitnessapp.models.RegisterRequest
import dev.alinda.myfitnessapp.models.RegisterResponse
import dev.alinda.myfitnessapp.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleclick()
    }

    fun handleclick() {
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            validateSignUp()
        }
    }


    override fun onResume(){
        super.onResume()

        //when a response comes back after a request we have been obsserving
        userViewModel.signupLiveData.observe(this, Observer { registerResponse->
            Toast.makeText(baseContext, registerResponse?.message, Toast.LENGTH_LONG).show()
            //persistSignupDetails(RegisterResponse!!)
            startActivity(Intent(baseContext,LoginActivity::class.java))

        })
        userViewModel.signUpError.observe(this, Observer{ errorMsg ->
            Toast.makeText(baseContext, errorMsg, Toast.LENGTH_LONG).show()

        })
    }

    fun validateSignUp() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var phoneNumber = binding.etphoneNumber.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()
        val userViewModel: UserViewModel by viewModels()

        var error = false
        if (firstName.isBlank()) {
            error = true
            binding.tilFirstName.error = "First Name is required"
        }
        if (lastName.isBlank()) {
            error = true
            binding.tilLastName.error = "Last Name is required"
        }
        if (phoneNumber.isBlank()) {
            error = true
            binding.tilphoneNumber.error = "Last Name is required"
        }
        if (email.isBlank()) {
            error = true
            binding.tilEmail.error = "Email is required"
        }
        if (!error) {
            binding.pbRegisterr.visibility = View.VISIBLE
            var RegisterRequest = RegisterRequest(firstName, lastName, phoneNumber, password, email)
            userViewModel.signUp(RegisterRequest)
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email is invalid"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (confirmPassword.isBlank()) {
            binding.tilConfirmPassword.error = "Password confirmation is required"
        }
        if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Incorrect confirmation"
        }

    }
}