package dev.alinda.myfitnessapp.api

import dev.alinda.myfitnessapp.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiInterface{
    @POST("/register")
     suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
   suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/profile")
    suspend fun profileUser(@Body profileRequest: ProfileRequest): Response<ProfileResponse>

    @GET("/exercise-categories")
    suspend fun fetchExcerciseCategories(@Header ("Authorization") token: String): Response<List<ExcerciseCategory>>
}