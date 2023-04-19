package com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO

import android.hardware.camera2.params.MandatoryStreamCombination.MandatoryStreamInformation
import com.google.gson.annotations.SerializedName

data class ShopDetail(
    @SerializedName("storeId") var storeId : Long,
    @SerializedName("StoreName")var storeName : String,
    @SerializedName("information")var information:String,
    @SerializedName("storeImgURL")var storeImg:String?
)
