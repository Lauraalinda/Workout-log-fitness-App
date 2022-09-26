package dev.alinda.myfitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.alinda.myfitnessapp.api.ApiClient
import dev.alinda.myfitnessapp.api.ApiInterface
import dev.alinda.myfitnessapp.databinding.ActivityLoginBinding
import dev.alinda.myfitnessapp.models.LoginRequest
import dev.alinda.myfitnessapp.models.LoginResponse
import dev.alinda.myfitnessapp.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback



class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences //helps one to stay logged in if they were already logged in
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleclick()
       sharedPrefs = getSharedPreferences("MYFITNESSAPP_PREFS", MODE_PRIVATE)

    }

    fun handleclick() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    override fun onResume(){
        super.onResume()
        //when a response comes back after a request we have been obsserving
        userViewModel.loginLiveData.observe(this, Observer { loginResponse->
            //incase anything happens the activity will know and excecute the code below since it has been observing

            //so as soon as sth changes the activity will know becuase it has been observing
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            persistLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))

        })
        userViewModel.loginError.observe(this, Observer{ errorMsg ->
            Toast.makeText(baseContext, errorMsg, Toast.LENGTH_LONG).show()

        })
    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tilEmail.error = null

            }
        })


        if (email.isBlank()) {
            error = true
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassword.error = "Password is required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = true
            binding.tilEmail.error = "Email is invalid"
        }
        if (!error){
            val loginRequest = LoginRequest(email, password)
            userViewModel.login(loginRequest)
        }
    }

    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor= sharedPrefs.edit()
        val token = "Bearer ${loginResponse.accessToken}"
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("ACCESS_TOKEN", token)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
}


