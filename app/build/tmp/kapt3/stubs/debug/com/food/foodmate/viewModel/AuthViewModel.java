package com.food.foodmate.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020:J&\u0010G\u001a\u00020E2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010:2\b\b\u0002\u0010I\u001a\u00020J2\b\b\u0002\u0010K\u001a\u00020JJ\u000e\u0010L\u001a\u00020E2\u0006\u0010F\u001a\u00020:J&\u0010 \u001a\u00020E2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010:2\b\b\u0002\u0010I\u001a\u00020J2\b\b\u0002\u0010K\u001a\u00020JJ\"\u0010#\u001a\u00020E2\u0006\u0010M\u001a\u00020:2\b\b\u0002\u0010I\u001a\u00020J2\b\b\u0002\u0010K\u001a\u00020JJ\u0014\u0010N\u001a\u00020E2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020E0PJ\u0006\u0010Q\u001a\u00020EJ\u000e\u0010R\u001a\u00020E2\u0006\u0010S\u001a\u00020:J\u000e\u0010T\u001a\u00020E2\u0006\u0010U\u001a\u00020:J\u000e\u0010V\u001a\u00020E2\u0006\u0010W\u001a\u00020:J\u000e\u0010X\u001a\u00020E2\u0006\u0010Y\u001a\u00020\u000fJ,\u0010Z\u001a\u00020E2\u0006\u0010[\u001a\u00020:2\u0006\u0010U\u001a\u00020:2\u0006\u0010\\\u001a\u00020:2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020E0PJ\u0016\u0010]\u001a\u00020E2\u0006\u0010^\u001a\u00020:2\u0006\u0010_\u001a\u00020:J\u000e\u0010`\u001a\u00020E2\u0006\u0010a\u001a\u00020bJP\u0010c\u001a\u00020E2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010:2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010:2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010:2\n\b\u0002\u0010e\u001a\u0004\u0018\u00010:2\n\b\u0002\u0010f\u001a\u0004\u0018\u00010:2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020E0PR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001a8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u001cR\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0+\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010,R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b.\u0010\u001cR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0013008F\u00a2\u0006\u0006\u001a\u0004\b1\u00102R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000b0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b4\u0010\u001cR\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001a8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010\u001cR\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b8\u0010\u001cR\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020:0+\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010,R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020:0+\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010,R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020:0+\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010,R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020:0+\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010,R\u0019\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010:0+\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010,\u00a8\u0006g"}, d2 = {"Lcom/food/foodmate/viewModel/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "dataStoreManager", "Lcom/food/foodmate/utility/DataStoreManager;", "(Lcom/food/foodmate/utility/DataStoreManager;)V", "_authState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/food/foodmate/viewModel/AuthState;", "_campaignDetails", "Lcom/food/foodmate/dataModel/campaign/Campaign;", "_campaignList", "", "_foodList", "Lcom/food/foodmate/dataModel/products/Food;", "_isLoading", "", "_loading", "_loginUiState", "Landroidx/compose/runtime/MutableState;", "Lcom/food/foodmate/viewModel/LoginUiState;", "_restaurantList", "Lcom/food/foodmate/dataModel/shop/Restaurant;", "_shopDetails", "Lcom/food/foodmate/dataModel/shop/Shop;", "_updateState", "authState", "Lkotlinx/coroutines/flow/StateFlow;", "getAuthState", "()Lkotlinx/coroutines/flow/StateFlow;", "campaignDetails", "getCampaignDetails", "campaignList", "getCampaignList", "campaignShopList", "Lcom/food/foodmate/dataModel/campaign/CampaignShops;", "getCampaignShopList", "campaignShops", "getDataStoreManager", "()Lcom/food/foodmate/utility/DataStoreManager;", "foodList", "getFoodList", "isLoading", "isLoggedIn", "Lkotlinx/coroutines/flow/Flow;", "()Lkotlinx/coroutines/flow/Flow;", "loading", "getLoading", "loginUiState", "Landroidx/compose/runtime/State;", "getLoginUiState", "()Landroidx/compose/runtime/State;", "restaurantList", "getRestaurantList", "shopDetails", "getShopDetails", "updateState", "getUpdateState", "userBio", "", "getUserBio", "userEmail", "getUserEmail", "userMobile", "getUserMobile", "userName", "getUserName", "userProfile", "getUserProfile", "fetchRestaurantFoods", "", "shopId", "fetchRestaurants", "key", "page", "", "limit", "fetchShopDetails", "campaignId", "login", "onSuccess", "Lkotlin/Function0;", "logout", "onMobileNumber", "newMobileNumber", "onName", "name", "onPasswordChange", "newPassword", "onRememberMeChange", "checked", "registerUser", "mobileNumber", "password", "saveToken", "id", "token", "saveUserSession", "user", "Lcom/food/foodmate/dataModel/auth/LoginResponse;", "updateUserProfile", "email", "bioDescription", "profilePicture", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.food.foodmate.utility.DataStoreManager dataStoreManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLoggedIn = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userMobile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userBio = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.viewModel.AuthState> _authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<com.food.foodmate.viewModel.LoginUiState> _loginUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.viewModel.AuthState> _updateState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.food.foodmate.dataModel.shop.Restaurant>> _restaurantList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.dataModel.shop.Shop> _shopDetails = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.food.foodmate.dataModel.products.Food>> _foodList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.products.Food>> foodList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.food.foodmate.dataModel.campaign.Campaign>> _campaignList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.campaign.Campaign>> campaignList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.food.foodmate.dataModel.campaign.CampaignShops>> campaignShops = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.campaign.CampaignShops>> campaignShopList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.dataModel.campaign.Campaign> _campaignDetails = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.utility.DataStoreManager dataStoreManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.utility.DataStoreManager getDataStoreManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLoggedIn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserMobile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserBio() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.food.foodmate.viewModel.AuthState> getAuthState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<com.food.foodmate.viewModel.LoginUiState> getLoginUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.food.foodmate.viewModel.AuthState> getUpdateState() {
        return null;
    }
    
    public final void onMobileNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String newMobileNumber) {
    }
    
    public final void onName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
    
    public final void onRememberMeChange(boolean checked) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getLoading() {
        return null;
    }
    
    public final void registerUser(@org.jetbrains.annotations.NotNull()
    java.lang.String mobileNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void updateUserProfile(@org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String mobileNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String bioDescription, @org.jetbrains.annotations.Nullable()
    java.lang.String profilePicture, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void saveToken(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void saveUserSession(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.dataModel.auth.LoginResponse user) {
    }
    
    public final void logout() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.shop.Restaurant>> getRestaurantList() {
        return null;
    }
    
    public final void fetchRestaurants(@org.jetbrains.annotations.Nullable()
    java.lang.String key, int page, int limit) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.food.foodmate.dataModel.shop.Shop> getShopDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void fetchShopDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String shopId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.products.Food>> getFoodList() {
        return null;
    }
    
    public final void fetchRestaurantFoods(@org.jetbrains.annotations.NotNull()
    java.lang.String shopId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.campaign.Campaign>> getCampaignList() {
        return null;
    }
    
    public final void getCampaignList(@org.jetbrains.annotations.Nullable()
    java.lang.String key, int page, int limit) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.dataModel.campaign.CampaignShops>> getCampaignShopList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.food.foodmate.dataModel.campaign.Campaign> getCampaignDetails() {
        return null;
    }
    
    public final void getCampaignShopList(@org.jetbrains.annotations.NotNull()
    java.lang.String campaignId, int page, int limit) {
    }
}