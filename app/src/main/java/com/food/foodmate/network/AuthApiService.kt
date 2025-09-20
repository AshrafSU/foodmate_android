package com.food.foodmate.network


import com.food.foodmate.dataModel.ApiResponse
import com.food.foodmate.dataModel.auth.LoginResponse
import com.food.foodmate.dataModel.campaign.CampaignResponse
import com.food.foodmate.dataModel.campaign.CampaignShopResponse
import com.food.foodmate.dataModel.products.FoodResponse
import com.food.foodmate.dataModel.shop.RestaurantResponse
import com.food.foodmate.dataModel.shop.ShopDetailsResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

data class LoginRequest(val mobileNumberOrEmail: String, val password: String)
data class SignupRequest(val name: String, val mobileNumberOrEmail: String, val password: String)
data class UpdateUserRequest(
    val id: String, // Required
    val name: String? = null,
    val email: String? = null,
    val mobileNumber: String? = null,
    val password: String? = null,
    val bioDescription: String? = null,
    val profilePicture: String? = null
)
data class RegisterUserRequest(
    @SerializedName("mobileNumber") val mobileNumber: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)



interface AuthApiService {
    @POST("user")
    suspend fun registerUser(
        @Body request: RegisterUserRequest
    ): Response<ApiResponse>

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): Response<LoginResponse>

    @PUT("user/update")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Body request: UpdateUserRequest
    ): Response<ApiResponse>

    @GET("shop/all")
    suspend fun getRestaurantList(
        @Query("key") key: String? = null,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<RestaurantResponse>

    @GET("shop/details")
    suspend fun getShopDetails(
        @Query("id") shopId: String
    ): Response<ShopDetailsResponse>

    @GET("food/all")
    suspend fun getRestaurantFoods(
        @Query("shopId") shopId: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<FoodResponse>

    @GET("campaign/all")
    suspend fun getAllCampaign(
        @Query("key") key: String? = null,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<CampaignResponse>

    @GET("campaign/campaign-shop")
    suspend fun getCampaignShops(
        @Query("campaignId") campaignId: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<CampaignShopResponse>


}


