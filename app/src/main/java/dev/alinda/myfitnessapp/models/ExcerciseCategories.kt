package dev.alinda.myfitnessapp.models

import com.google.gson.annotations.SerializedName

data class ExcerciseCategory(
    @SerializedName("category_name")var categoryName: String,
    @SerializedName("category_id") var categoryId: String
)
