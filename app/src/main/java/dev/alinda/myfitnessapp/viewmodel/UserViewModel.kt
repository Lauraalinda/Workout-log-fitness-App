package dev.alinda.myfitnessapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alinda.myfitnessapp.models.LoginRequest
import dev.alinda.myfitnessapp.models.LoginResponse
import dev.alinda.myfitnessapp.models.RegisterRequest
import dev.alinda.myfitnessapp.models.RegisterResponse
import dev.alinda.myfitnessapp.repository.UserRepository
import dev.alinda.myfitnessapp.ui.SignupActivity
import kotlinx.coroutines.launch

//middleman between ui and repository

class UserViewModel: ViewModel(){
    //instantiate a user respository
    val userRepository = UserRepository()
    val loginLiveData =MutableLiveData<LoginResponse>()//mutable live data holder that will hold our login response
    val signupLiveData=MutableLiveData<RegisterResponse>()
    val signUpError = MutableLiveData<String>()
    val loginError = MutableLiveData<String>()
    //function that creates a coroutine for us
  fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {// since this co is created in the vm its only alive when the views is alive wc is why we
          val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                loginLiveData.postValue(response.body()) //this post value function takes the recieved data from thee io thread and
                //posts on the main thread where the observers are
            }else{
                loginError.postValue(response.errorBody()?.string())
        }
      }// livedata is an object that holds data ..is an observable data holder so it tells the
  // activity incase of any changes like here above the login and error
    }

    fun signUp(signupRequest:  RegisterRequest) {
        viewModelScope.launch {
            val response =userRepository.signupUser(signupRequest)
             if (response.isSuccessful){
                 signupLiveData.postValue(response.body())
             }else{
                 signUpError.postValue(response.errorBody()?.string())
             }
        }
    }

}