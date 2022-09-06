package dev.alinda.myfitnessapp.models

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("user_id")var userId:String,
    var sex: String,
    @SerializedName("date_of_birth")var dateofbirth: String,
    var weight:Int,
    var height:Int
)
