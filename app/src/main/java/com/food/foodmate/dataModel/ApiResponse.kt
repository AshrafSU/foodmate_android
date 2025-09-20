package com.food.foodmate.dataModel

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("message")
    val message: String = "",

    @SerializedName("statusCode")
    val statusCode: Int = 0
)
