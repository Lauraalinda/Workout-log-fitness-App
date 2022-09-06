package dev.alinda.myfitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("MYFITNESSAPP_PREFS", MODE_PRIVATE)

        var accessToken = sharedPrefs.getString("ACCESS_TOKEN", "").toString()
        if (accessToken.isBlank()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}