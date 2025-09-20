package com.food.foodmate.dataModel.shop

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("shops") val shops: List<Restaurant>
)

data class Restaurant(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("banner") val banner: String,
    @SerializedName("address") val address: String,
    @SerializedName("location") val location: Location,
    @SerializedName("deliveryCharge") val deliveryCharge: Int,
    @SerializedName("rating") val rating: Float?,
    @SerializedName("deliveryTime") val deliveryTime: String,
    @SerializedName("allowMealOrder") val allowMealOrder: Boolean,
    @SerializedName("allowDine") val allowDine: Boolean
)

data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)
