package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class PurchaseSeatData(
    @SerializedName("menuListReqDto")
    val menuListReq: List<PurchaseMenuData>,
)

data class PurchaseMenuData(
    val menuId: Long,
    val quantity: Long,
)