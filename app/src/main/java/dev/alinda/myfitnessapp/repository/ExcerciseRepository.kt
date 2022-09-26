package dev.alinda.myfitnessapp.repository

import android.media.session.MediaSession
import dev.alinda.myfitnessapp.api.ApiClient
import dev.alinda.myfitnessapp.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExcerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface:: class.java)
    suspend fun fetchExcerciseCategories(accessToken: String)
    = withContext(Dispatchers.IO){
        return@withContext apiClient.fetchExcerciseCategories(accessToken)
    }
}