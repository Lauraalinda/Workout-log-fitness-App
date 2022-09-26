package dev.alinda.myfitnessapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alinda.myfitnessapp.models.ExcerciseCategory
import dev.alinda.myfitnessapp.repository.ExcerciseRepository
import kotlinx.coroutines.launch

class ExcerciseViewModel: ViewModel(){
   val excerciseRepository = ExcerciseRepository()
    val excerciseCategoryLiveData =MutableLiveData<List<ExcerciseCategory>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchExcerciseCategories(accessToken: String){
        viewModelScope.launch {
            val response = excerciseRepository.fetchExcerciseCategories(accessToken)
            if (response.isSuccessful) {
                excerciseCategoryLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}