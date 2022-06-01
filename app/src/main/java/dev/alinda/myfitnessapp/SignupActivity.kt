package dev.alinda.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var tvLogin: TextView
    lateinit var etFirstName: TextInputEditText
    lateinit var etLastName: TextInputEditText
    lateinit var etEmail:TextInputEditText
    lateinit var etPassword: TextInputEditText
    lateinit var etConfirmPassword: TextInputEditText
    lateinit var tilFirstName: TextInputLayout
    lateinit var tilLastName: TextInputLayout
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var tilConfirmPassword: TextInputLayout
    lateinit var btnSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        tvLogin=findViewById(R.id.tvLogin)
        etFirstName=findViewById(R.id.etFirstName)
        etLastName=findViewById(R.id.etLastName)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)
        tilFirstName=findViewById(R.id.tilFirstName)
        tilLastName=findViewById(R.id.tilLastName)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        tilConfirmPassword=findViewById(R.id.tilConfirmPassword)
        btnSignUp=findViewById(R.id.btnSignUp)


        tvLogin.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            validateSignUp()
        }
    }

    fun validateSignUp(){
        var firstName= etFirstName.text.toString()
        var lastName= etLastName.text.toString()
        var email= etEmail.text.toString()
        var password=etPassword.text.toString()
        var confirmPassword=etConfirmPassword.text.toString()
        if (firstName.isBlank()){
            tilFirstName.error="First Name is required"
        }
        if (lastName.isBlank()){
            tilLastName.error="Last Name is required"
        }
        if (email.isBlank()){
            tilEmail.error="Email is required"
        }
        if (password.isBlank()){
            tilPassword.error="Password is required"
        }
        if (confirmPassword.isBlank()){
            tilConfirmPassword.error="Password confirmation is required"
        }
    }
}