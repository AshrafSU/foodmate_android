package com.food.foodmate.views.cartScreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a$\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a6\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"CartItemRow", "", "cartItem", "Lcom/food/foodmate/viewModel/CartItem;", "cartViewModel", "Lcom/food/foodmate/viewModel/CartViewModel;", "CartScreen", "navController", "Landroidx/navigation/NavHostController;", "viewModel", "Lcom/food/foodmate/viewModel/AuthViewModel;", "CheckoutSummary", "subTotal", "", "deliveryCharge", "discount", "total", "onCheckout", "Lkotlin/Function0;", "app_debug"})
public final class CartScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void CartScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.AuthViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CartItemRow(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartItem cartItem, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartViewModel cartViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CheckoutSummary(double subTotal, double deliveryCharge, double discount, double total, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCheckout) {
    }
}