package com.food.foodmate.dataModel.campaign

import com.food.foodmate.dataModel.shop.Restaurant
import com.google.gson.annotations.SerializedName

data class CampaignResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("campaigns") val campaigns: List<Campaign>
)

data class Campaign(
@SerializedName("id") val id: String,
@SerializedName("name") val name: String,
@SerializedName("description") val description: String?,
@SerializedName("banner") val banner: String?,
@SerializedName("logo") val logo: String
)


data class CampaignShopResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("campaignDetails") val campaignDetails: Campaign,
    @SerializedName("campaignShops") val campaignShops: List<CampaignShops>
)
data class CampaignShops(
    @SerializedName("id") val id: String,
    @SerializedName("campaignId") val campaignId: String,
    @SerializedName("campaign") val banner: Campaign?,
    @SerializedName("shopId") val shopId: String,
    @SerializedName("shop") val shop: Restaurant?,
    @SerializedName("isActive") val isActive: Boolean
)