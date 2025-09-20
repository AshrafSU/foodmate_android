package com.food.foodmate.views.restaurantDetails;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\\\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u001aV\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u001aV\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u001a&\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u001a,\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u001c"}, d2 = {"CategorizedFoodList", "", "product", "", "Lcom/food/foodmate/dataModel/products/Food;", "cartViewModel", "Lcom/food/foodmate/viewModel/CartViewModel;", "shopId", "", "shopName", "shopLogo", "deliveryCharge", "", "allowMealOrder", "", "allowDine", "onProductClick", "Lkotlin/Function0;", "ProductCard", "ProductCell", "ProductDetailsBottomSheet", "onClose", "RestaurantDetailsScreen", "navController", "Landroidx/navigation/NavController;", "restaurantId", "viewModel", "Lcom/food/foodmate/viewModel/AuthViewModel;", "app_debug"})
public final class RestaurantDetailsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class, androidx.compose.material.ExperimentalMaterialApi.class})
    @androidx.compose.runtime.Composable()
    public static final void RestaurantDetailsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.AuthViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProductCard(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.dataModel.products.Food product, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel, @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.NotNull()
    java.lang.String shopName, @org.jetbrains.annotations.NotNull()
    java.lang.String shopLogo, double deliveryCharge, boolean allowMealOrder, boolean allowDine, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProductClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CategorizedFoodList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.food.foodmate.dataModel.products.Food> product, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel, @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.NotNull()
    java.lang.String shopName, @org.jetbrains.annotations.NotNull()
    java.lang.String shopLogo, double deliveryCharge, boolean allowMealOrder, boolean allowDine, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProductClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProductCell(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.dataModel.products.Food product, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel, @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.NotNull()
    java.lang.String shopName, @org.jetbrains.annotations.NotNull()
    java.lang.String shopLogo, double deliveryCharge, boolean allowMealOrder, boolean allowDine, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProductClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProductDetailsBottomSheet(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.dataModel.products.Food product, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClose) {
    }
}