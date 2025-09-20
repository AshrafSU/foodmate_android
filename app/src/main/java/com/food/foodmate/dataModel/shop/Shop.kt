package com.food.foodmate.dataModel.shop

import com.google.gson.annotations.SerializedName

data class ShopDetailsResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("shop") val shop: Shop
)

data class Shop(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("banner") val banner: String,
    @SerializedName("address") val address: String,
    @SerializedName("deliveryCharge") val deliveryCharge: Double,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("deliveryTime") val deliveryTime: String,
    @SerializedName("allowMealOrder") val allowMealOrder: Boolean,
    @SerializedName("allowDine") val allowDine: Boolean
)
