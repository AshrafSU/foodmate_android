package com.food.foodmate.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/food/foodmate/viewModel/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cartDao", "Lcom/food/foodmate/viewModel/CartDao;", "app_debug"})
@androidx.room.Database(entities = {com.food.foodmate.viewModel.CartItem.class}, version = 3)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.food.foodmate.viewModel.CartDao cartDao();
}