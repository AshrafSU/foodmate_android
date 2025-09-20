package com.food.foodmate.dataModel.products

import com.food.foodmate.viewModel.CartItem
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("foods") val foods: List<Food>
)

data class Food(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Double,
    @SerializedName("sellPrice") val sellPrice: Double,
    @SerializedName("discount") val discount: Double?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("categoryId") val categoryId: String,
    @SerializedName("shopId") val shopId: String,
    @SerializedName("isPopular") val isPopular: Boolean,
    @SerializedName("category") val category: Category,
    @SerializedName("shop") val shop: Shop
)

fun Food.toCartItem(): CartItem {
    return CartItem(
        foodId = this.id,
        name = this.name,
        quantity = 1,
        price = this.price,
        sellPrice = this.sellPrice,
        imageUrl = this.imageUrl,
        shopId = this.shop.id,
        shopName = this.shop.name,
        shopLogo = this.shop.logo,
        deliveryCharge = this.shop.deliveryCharge,
        allowMealOrder = this.shop.allowMealOrder,
        allowDine = this.shop.allowDine
    )
}

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class Shop(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("banner") val banner: String,
    @SerializedName("location") val location: Location,
    @SerializedName("deliveryCharge") val deliveryCharge: Double,
    @SerializedName("allowMealOrder") val allowMealOrder: Boolean,
    @SerializedName("allowDine") val allowDine: Boolean

)

data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)