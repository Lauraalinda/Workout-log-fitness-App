package dev.alinda.myfitnessapp.repository

import dev.alinda.myfitnessapp.api.ApiClient
import dev.alinda.myfitnessapp.api.ApiInterface
import dev.alinda.myfitnessapp.models.LoginRequest
import dev.alinda.myfitnessapp.models.LoginResponse
import dev.alinda.myfitnessapp.models.RegisterRequest
import dev.alinda.myfitnessapp.models.RegisterResponse
import dev.alinda.myfitnessapp.ui.SignupActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    //for making our login and register requests //data source
    val apiClient = ApiClient.buildApiClient(ApiInterface:: class.java)
    // login call
    // a suspend function means that ths func will suspend itself at soem point when its waiting
    //for a response
    suspend fun  loginUser(loginRequest: LoginRequest): Response<LoginResponse> = withContext(Dispatchers.IO)
    //dispatchers.io -the set of threads in the coroutine(io..long running tasks)
    {
        val response =apiClient.loginUser(loginRequest)
        return@withContext response
    }
    suspend fun signupUser(signUpRequest: RegisterRequest): Response<RegisterResponse> = withContext(Dispatchers.IO)
    {
        val response=apiClient.registerUser(signUpRequest)
        return@withContext response
    }


}
