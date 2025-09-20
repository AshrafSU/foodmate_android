package com.food.foodmate.views.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\b\u0010\u0004\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0007\u001a\b\u0010\u0007\u001a\u00020\u0001H\u0007\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a&\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u001a<\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\rH\u0007\u001a&\u0010\u001e\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\r2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\u0018\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020 2\u0006\u0010\n\u001a\u00020\rH\u0007\u00a8\u0006#"}, d2 = {"CuisineChip", "", "name", "", "CuisinesSection", "GreetingSection", "userName", "HomeScreen", "viewModel", "Lcom/food/foodmate/viewModel/AuthViewModel;", "navController", "Landroidx/navigation/NavHostController;", "ImageCarousel", "Landroidx/navigation/NavController;", "campaigns", "", "Lcom/food/foodmate/dataModel/campaign/Campaign;", "isLoading", "", "MealOptionCard", "title", "subtitle", "discount", "imageRes", "", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "MealOptionsSection", "NearRestaurantsSection", "restaurants", "Lcom/food/foodmate/dataModel/shop/Restaurant;", "RestaurantCard", "restaurant", "app_debug"})
public final class HomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.AuthViewModel viewModel, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen() {
    }
    
    @android.annotation.SuppressLint(value = {"RememberReturnType"})
    @androidx.compose.runtime.Composable()
    public static final void GreetingSection(@org.jetbrains.annotations.NotNull()
    java.lang.String userName) {
    }
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.pager.ExperimentalPagerApi.class})
    @androidx.compose.runtime.Composable()
    public static final void ImageCarousel(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.util.List<com.food.foodmate.dataModel.campaign.Campaign> campaigns, boolean isLoading) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MealOptionsSection(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MealOptionCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    java.lang.String discount, int imageRes, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.Shape shape) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CuisinesSection() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CuisineChip(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NearRestaurantsSection(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.util.List<com.food.foodmate.dataModel.shop.Restaurant> restaurants, boolean isLoading) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RestaurantCard(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.dataModel.shop.Restaurant restaurant, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
}