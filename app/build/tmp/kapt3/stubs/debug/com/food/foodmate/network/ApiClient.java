package com.food.foodmate.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\f\u0010\u0006R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001b\u0010\u0006\u00a8\u0006\u001d"}, d2 = {"Lcom/food/foodmate/network/ApiClient;", "", "()V", "authApiService", "Lcom/food/foodmate/network/AuthApiService;", "getAuthApiService", "()Lcom/food/foodmate/network/AuthApiService;", "authApiService$delegate", "Lkotlin/Lazy;", "client", "Lokhttp3/OkHttpClient;", "foodApiService", "getFoodApiService", "foodApiService$delegate", "loggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "orderApiService", "Lcom/food/foodmate/network/OrderApiService;", "getOrderApiService", "()Lcom/food/foodmate/network/OrderApiService;", "orderApiService$delegate", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "shopApiService", "getShopApiService", "shopApiService$delegate", "app_debug"})
public final class ApiClient {
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.logging.HttpLoggingInterceptor loggingInterceptor = null;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy retrofit$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy authApiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy shopApiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy foodApiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy orderApiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.food.foodmate.network.ApiClient INSTANCE = null;
    
    private ApiClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.network.AuthApiService getAuthApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.network.AuthApiService getShopApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.network.AuthApiService getFoodApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.network.OrderApiService getOrderApiService() {
        return null;
    }
}