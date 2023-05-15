package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class ShopDetailData(
    @SerializedName("storeId") val storeId: Long,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("information") val information: String,
    @SerializedName("storeImgURL") val storeImg: String?
)
