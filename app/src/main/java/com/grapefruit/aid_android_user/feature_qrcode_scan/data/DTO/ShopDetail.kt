package com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO

import android.hardware.camera2.params.MandatoryStreamCombination.MandatoryStreamInformation
import com.google.gson.annotations.SerializedName

data class ShopDetail(
    @SerializedName("storeId") val storeId: Long,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("information") val information: String,
    @SerializedName("storeImgURL") val storeImg: String?
)
