package dev.alinda.myfitnessapp.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.alinda.myfitnessapp.R
import dev.alinda.myfitnessapp.viewmodel.ExcerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var bnvHome:BottomNavigationView
    lateinit var fcvHome:FragmentContainerView
    lateinit var sharedPrefs : SharedPreferences

    val excerciseViewModel: ExcerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
        setupBottomNav()
        triggerFetchExerciseCategories()

    }

    override fun onResume(){
        super.onResume()
        excerciseViewModel.excerciseCategoryLiveData.observe(this,
            {categoryResponse->
                Toast.makeText(this, "fetched ${categoryResponse.size}", Toast.LENGTH_LONG).show()
    })

        excerciseViewModel.excerciseCategoryLiveData.observe(this,
            {errorMsg -> Toast.makeText(this, "fetched ${errorMsg.size}", Toast.LENGTH_LONG).show()})

    }
            fun triggerFetchExerciseCategories() {
                sharedPrefs = getSharedPreferences("FITMAX_PREFS", MODE_PRIVATE)
                val getAccessToken = sharedPrefs.getString("ACCESS_TOKEN", "")
                excerciseViewModel.fetchExcerciseCategories(getAccessToken!!)

            }

    fun castViews(){
        bnvHome=findViewById(R.id.bottom_navigation)
        fcvHome=findViewById(R.id.fcvHome)
    }
    fun setupBottomNav(){
        bnvHome.setOnItemSelectedListener{ item->
            when(item.itemId){
                R.id.plan ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
            }
                else-> false
        }

        }
    }
}