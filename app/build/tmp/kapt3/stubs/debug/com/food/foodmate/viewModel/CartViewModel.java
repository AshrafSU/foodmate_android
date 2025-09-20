package com.food.foodmate.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0#J\u0006\u0010$\u001a\u00020 J\u0006\u0010%\u001a\u00020&J\u001a\u0010\'\u001a\u00020 2\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020)J\u000e\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020\u0017J9\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00172\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\r2\n\b\u0002\u00101\u001a\u0004\u0018\u00010)\u00a2\u0006\u0002\u00102J\u000e\u00103\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0013J\u000e\u00104\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0013R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/food/foodmate/viewModel/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/food/foodmate/viewModel/CartRepository;", "dataStoreManager", "Lcom/food/foodmate/utility/DataStoreManager;", "(Lcom/food/foodmate/viewModel/CartRepository;Lcom/food/foodmate/utility/DataStoreManager;)V", "_isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_orderDetails", "Lcom/food/foodmate/network/OrderData;", "_orderList", "", "Lcom/food/foodmate/network/Order;", "_orderState", "Lcom/food/foodmate/viewModel/OrderState;", "cartItems", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/food/foodmate/viewModel/CartItem;", "getCartItems", "()Lkotlinx/coroutines/flow/StateFlow;", "currentShopId", "", "isLoading", "myOrderList", "getMyOrderList", "orderDetails", "getOrderDetails", "orderState", "getOrderState", "addToCart", "", "item", "onShowClearCartDialog", "Lkotlin/Function0;", "clearCart", "getDeliveryCharge", "", "getMyOrders", "page", "", "limit", "getOrderDetailsByOrderId", "orderId", "placeOrder", "deliveryType", "paymentMethod", "reOrderDays", "seatNumber", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;)V", "removeFromCart", "replaceCartWithNewItem", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.food.foodmate.viewModel.CartRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.food.foodmate.utility.DataStoreManager dataStoreManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.viewModel.CartItem>> cartItems = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentShopId;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.viewModel.OrderState> _orderState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.food.foodmate.network.Order>> _orderList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.food.foodmate.network.OrderData> _orderDetails = null;
    
    @javax.inject.Inject()
    public CartViewModel(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartRepository repository, @org.jetbrains.annotations.NotNull()
    com.food.foodmate.utility.DataStoreManager dataStoreManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.viewModel.CartItem>> getCartItems() {
        return null;
    }
    
    public final void addToCart(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartItem item, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onShowClearCartDialog) {
    }
    
    public final void removeFromCart(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartItem item) {
    }
    
    public final void replaceCartWithNewItem(@org.jetbrains.annotations.NotNull()
    com.food.foodmate.viewModel.CartItem item) {
    }
    
    public final void clearCart() {
    }
    
    public final double getDeliveryCharge() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.food.foodmate.viewModel.OrderState> getOrderState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void placeOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String deliveryType, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> reOrderDays, @org.jetbrains.annotations.Nullable()
    java.lang.Integer seatNumber) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.food.foodmate.network.Order>> getMyOrderList() {
        return null;
    }
    
    public final void getMyOrders(int page, int limit) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.food.foodmate.network.OrderData> getOrderDetails() {
        return null;
    }
    
    public final void getOrderDetailsByOrderId(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId) {
    }
}