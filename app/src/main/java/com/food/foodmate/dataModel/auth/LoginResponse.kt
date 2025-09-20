package com.food.foodmate.dataModel.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String = "",

    @SerializedName("statusCode")
    val statusCode: Int = 0,

    @SerializedName("id")
    val id: String = "",

    @SerializedName("token")
    val token: String? = null, // Nullable because it may not be returned in case of failure

    @SerializedName("name")
    val name: String = "",

    @SerializedName("mobileNumber")
    val mobileNumber: String = "",

    @SerializedName("email")
    val email: String? = null, // Nullable because some users may not have an email

    @SerializedName("password")
    val password: String? = null, // Can be null in API response

    @SerializedName("profilePicture")
    val profilePicture: String? = null, // Nullable, as some users may not have a profile picture

    @SerializedName("userType")
    val userType: String = "",

    @SerializedName("bioDescription")
    val bioDescription: String? = null // Nullable, because not every user has a bio
)

