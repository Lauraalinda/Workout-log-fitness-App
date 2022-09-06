package dev.alinda.myfitnessapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import dev.alinda.myfitnessapp.ApiClient
import dev.alinda.myfitnessapp.ApiInterface
import dev.alinda.myfitnessapp.databinding.ActivitySignupBinding
import dev.alinda.myfitnessapp.models.RegisterRequest
import dev.alinda.myfitnessapp.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
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


    fun validateSignUp() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var phoneNumber = binding.etphoneNumber.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()

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
            makeRegistrationRequest(RegisterRequest)
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

    fun makeRegistrationRequest(registerRequest: RegisterRequest) {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                binding.pbRegisterr.visibility = View.GONE
                if (response.isSuccessful) {
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))
                } //returns true if the sucess is between  check java notes
                else {
                    var error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }


            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}