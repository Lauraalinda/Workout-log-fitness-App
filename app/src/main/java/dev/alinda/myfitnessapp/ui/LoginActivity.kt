package dev.alinda.myfitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.alinda.myfitnessapp.ApiClient
import dev.alinda.myfitnessapp.ApiInterface
import dev.alinda.myfitnessapp.databinding.ActivityLoginBinding
import dev.alinda.myfitnessapp.models.LoginRequest
import dev.alinda.myfitnessapp.models.LoginResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    lateinit var sharedPrefs: SharedPreferences

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
            makeLoginRequest(loginRequest)
        }
    }

    fun makeLoginRequest(loginRequest: LoginRequest){
        val apiClient =ApiClient.buildApiClient(ApiInterface:: class.java)
        val request =apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {//import call back from retrofit and // import some other call for retrofit
            override fun onResponse(
                call: Call<LoginResponse>, response: retrofit2.Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
                    persistLoginDetails(loginResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                    //open Home
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
               Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor= sharedPrefs.edit()
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
}


