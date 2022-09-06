package dev.alinda.myfitnessapp.UI

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import dev.alinda.myfitnessapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleclick()
    }
       fun handleclick(){
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
    }
        fun validateLogin() {
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

       binding.etEmail.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    binding.tilEmail.error=null

                }


            })


            if (email.isBlank()) {
                binding.tilEmail.error = "Email is required"
            }
            if (password.isBlank()) {
                binding.tilPassword.error = "Password is required"
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.tilEmail.error ="Email is invalid"
            }

        }
    }


