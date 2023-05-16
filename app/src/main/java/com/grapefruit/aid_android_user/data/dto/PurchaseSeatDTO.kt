package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class PurchaseSeatDTO(
    @SerializedName("menuListReqDto")
    val menuListReq: List<PurchaseMenuDTO>,
)

data class PurchaseMenuDTO(
    val menuId: Long,
    val quantity: Long,
)