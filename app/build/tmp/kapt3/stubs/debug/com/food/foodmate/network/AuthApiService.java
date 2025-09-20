package com.food.foodmate.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ2\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ2\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ4\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0001\u0010\u0018\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010!J(\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010#\u001a\u00020\u00062\b\b\u0001\u0010\u0018\u001a\u00020$H\u00a7@\u00a2\u0006\u0002\u0010%\u00a8\u0006&"}, d2 = {"Lcom/food/foodmate/network/AuthApiService;", "", "getAllCampaign", "Lretrofit2/Response;", "Lcom/food/foodmate/dataModel/campaign/CampaignResponse;", "key", "", "page", "", "limit", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCampaignShops", "Lcom/food/foodmate/dataModel/campaign/CampaignShopResponse;", "campaignId", "getRestaurantFoods", "Lcom/food/foodmate/dataModel/products/FoodResponse;", "shopId", "getRestaurantList", "Lcom/food/foodmate/dataModel/shop/RestaurantResponse;", "getShopDetails", "Lcom/food/foodmate/dataModel/shop/ShopDetailsResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/food/foodmate/dataModel/auth/LoginResponse;", "request", "Lcom/food/foodmate/network/LoginRequest;", "(Lcom/food/foodmate/network/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "Lcom/food/foodmate/dataModel/ApiResponse;", "Lcom/food/foodmate/network/RegisterUserRequest;", "(Lcom/food/foodmate/network/RegisterUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signup", "Lcom/food/foodmate/network/SignupRequest;", "(Lcom/food/foodmate/network/SignupRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "token", "Lcom/food/foodmate/network/UpdateUserRequest;", "(Ljava/lang/String;Lcom/food/foodmate/network/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AuthApiService {
    
    @retrofit2.http.POST(value = "user")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.food.foodmate.network.RegisterUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.ApiResponse>> $completion);
    
    @retrofit2.http.POST(value = "user/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.food.foodmate.network.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.auth.LoginResponse>> $completion);
    
    @retrofit2.http.POST(value = "signup")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signup(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.food.foodmate.network.SignupRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.auth.LoginResponse>> $completion);
    
    @retrofit2.http.PUT(value = "user/update")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.food.foodmate.network.UpdateUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.ApiResponse>> $completion);
    
    @retrofit2.http.GET(value = "shop/all")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRestaurantList(@retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.Nullable()
    java.lang.String key, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.shop.RestaurantResponse>> $completion);
    
    @retrofit2.http.GET(value = "shop/details")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getShopDetails(@retrofit2.http.Query(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.shop.ShopDetailsResponse>> $completion);
    
    @retrofit2.http.GET(value = "food/all")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRestaurantFoods(@retrofit2.http.Query(value = "shopId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.products.FoodResponse>> $completion);
    
    @retrofit2.http.GET(value = "campaign/all")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCampaign(@retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.Nullable()
    java.lang.String key, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.campaign.CampaignResponse>> $completion);
    
    @retrofit2.http.GET(value = "campaign/campaign-shop")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCampaignShops(@retrofit2.http.Query(value = "campaignId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String campaignId, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.food.foodmate.dataModel.campaign.CampaignShopResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}