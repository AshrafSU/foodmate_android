package com.food.foodmate.network


import com.food.foodmate.utility.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val shopApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val foodApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val orderApiService: OrderApiService by lazy {
        retrofit.create(OrderApiService::class.java)
    }

}

