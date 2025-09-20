package com.food.foodmate.utility;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/food/foodmate/utility/Constant;", "", "()V", "BASE_URL", "", "OrderStatus", "app_debug"})
public final class Constant {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "http://10.11.35.105/api/v1/";
    @org.jetbrains.annotations.NotNull()
    public static final com.food.foodmate.utility.Constant INSTANCE = null;
    
    private Constant() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/food/foodmate/utility/Constant$OrderStatus;", "", "(Ljava/lang/String;I)V", "ONGOING", "COMPLETED", "app_debug"})
    public static enum OrderStatus {
        /*public static final*/ ONGOING /* = new ONGOING() */,
        /*public static final*/ COMPLETED /* = new COMPLETED() */;
        
        OrderStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.food.foodmate.utility.Constant.OrderStatus> getEntries() {
            return null;
        }
    }
}