package dev.alinda.myfitnessapp

import dev.alinda.myfitnessapp.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface{
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/profile")
    fun profileUser(@Body profileRequest: ProfileRequest): Call<ProfileResponse>
}