package com.food.foodmate.network

import com.food.foodmate.dataModel.ApiResponse
import com.food.foodmate.dataModel.shop.Restaurant
import com.food.foodmate.viewModel.FoodItemRequest
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

data class PlaceOrderRequest(
    val deliveryType: String,
    val foodItems: List<FoodItemRequest>,
    val paymentMethod: String,
    val reOrderWeekDayName: List<String>? = null,  // Now optional (nullable)
    val seatNumber: Int? = null
)

data class FoodItemRequest(
    val foodId: String,
    val quantity: Int
)

data class MyOrderResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("orders") val orders: List<Order>
)

data class Order(
    @SerializedName("id") val id: String,
    @SerializedName("orderId") val orderId: String,
    @SerializedName("status") val status: String,
    @SerializedName("total") val total: String,
    @SerializedName("shop") val shop: Restaurant,
    @SerializedName("deliveryTime") val deliveryTime: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("paymentStatus") val paymentStatus: String
)


interface OrderApiService {
    @POST("order") // API Endpoint for User Registration
    suspend fun placeOrder(
        @Header("Authorization") token: String,
        @Body request: PlaceOrderRequest
    ): Response<ApiResponse>

    @GET("order/my-orders")
    suspend fun getMyOrderList(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<MyOrderResponse>

    @GET("order/details")
    suspend fun getOrderDetails(
        @Header("Authorization") token: String,
        @Query("orderId") orderId: String
    ): Response<OrderDetailsResponse>
}

data class OrderDetailsResponse(
    val message: String,
    val statusCode: Int,
    val order: OrderData
)

data class OrderData(
    val orderId: String,
    val status: String,
    val subTotal: Int,
    val total: Int,
    val dueAmount: Int,
    val paidAmount: Int,
    val discountAmount: Int,
    val deliveryCharge: Int,
    val paymentMethod: String,
    val paymentStatus: String,
    val createdAt: String,
    val shop: ShopData
)

data class ShopData(
    val id: String,
    val name: String,
    val logo: String,
    val banner: String,
    val address: String,
    val location: LocationData,
    val deliveryCharge: Int,
    val rating: Double?,       // can be null
    val deliveryTime: String,
    val distance: Double?,     // can be null
    val deleted: Boolean,
    val allowMealOrder: Boolean,
    val allowDine: Boolean
)

data class LocationData(
    val latitude: Double,
    val longitude: Double
)
