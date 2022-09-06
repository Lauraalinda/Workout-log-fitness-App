package dev.alinda.myfitnessapp.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("date_of_birth")var dateofbirth: String,
    @SerializedName("profile_id") var profileId: String,
    var sex: String,
    @SerializedName("user_id") var userId: String,
    var height: Int,
    var weight: Int,
)


