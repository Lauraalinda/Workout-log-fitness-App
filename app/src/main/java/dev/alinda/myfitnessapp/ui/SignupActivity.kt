package dev.alinda.myfitnessapp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import dev.alinda.myfitnessapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
   lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleclick()
    }
   fun handleclick(){
        binding.tvLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
      binding.btnSignUp.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            validateSignUp()
        }
    }

    fun validateSignUp() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()

        var error=false
        if (firstName.isBlank()) {
            error=true
            binding.tilFirstName.error = "First Name is required"
        }
        if (lastName.isBlank()) {
            error=true
            binding.tilLastName.error = "Last Name is required"
        }
        if (email.isBlank()) {
            error=true
            binding.tilEmail.error = "Email is required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.error ="Email is invalid"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (confirmPassword.isBlank()) {
            binding.tilConfirmPassword.error = "Password confirmation is required"
        }
        if(password!=confirmPassword){
            binding.tilConfirmPassword.error= "Incorrect confirmation"
        }
    }
}