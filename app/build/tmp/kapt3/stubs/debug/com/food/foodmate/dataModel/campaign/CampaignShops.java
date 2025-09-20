package com.food.foodmate.dataModel.campaign;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u000bH\u00c6\u0003JI\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/food/foodmate/dataModel/campaign/CampaignShops;", "", "id", "", "campaignId", "banner", "Lcom/food/foodmate/dataModel/campaign/Campaign;", "shopId", "shop", "Lcom/food/foodmate/dataModel/shop/Restaurant;", "isActive", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/food/foodmate/dataModel/campaign/Campaign;Ljava/lang/String;Lcom/food/foodmate/dataModel/shop/Restaurant;Z)V", "getBanner", "()Lcom/food/foodmate/dataModel/campaign/Campaign;", "getCampaignId", "()Ljava/lang/String;", "getId", "()Z", "getShop", "()Lcom/food/foodmate/dataModel/shop/Restaurant;", "getShopId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class CampaignShops {
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "campaignId")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String campaignId = null;
    @com.google.gson.annotations.SerializedName(value = "campaign")
    @org.jetbrains.annotations.Nullable()
    private final com.food.foodmate.dataModel.campaign.Campaign banner = null;
    @com.google.gson.annotations.SerializedName(value = "shopId")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String shopId = null;
    @com.google.gson.annotations.SerializedName(value = "shop")
    @org.jetbrains.annotations.Nullable()
    private final com.food.foodmate.dataModel.shop.Restaurant shop = null;
    @com.google.gson.annotations.SerializedName(value = "isActive")
    private final boolean isActive = false;
    
    public CampaignShops(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String campaignId, @org.jetbrains.annotations.Nullable()
    com.food.foodmate.dataModel.campaign.Campaign banner, @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.Nullable()
    com.food.foodmate.dataModel.shop.Restaurant shop, boolean isActive) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCampaignId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.food.foodmate.dataModel.campaign.Campaign getBanner() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getShopId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.food.foodmate.dataModel.shop.Restaurant getShop() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.food.foodmate.dataModel.campaign.Campaign component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.food.foodmate.dataModel.shop.Restaurant component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.food.foodmate.dataModel.campaign.CampaignShops copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String campaignId, @org.jetbrains.annotations.Nullable()
    com.food.foodmate.dataModel.campaign.Campaign banner, @org.jetbrains.annotations.NotNull()
    java.lang.String shopId, @org.jetbrains.annotations.Nullable()
    com.food.foodmate.dataModel.shop.Restaurant shop, boolean isActive) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}