package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class SeatData(
    @SerializedName("seatId") val seatId: Long,
    @SerializedName("seatNum") val seatNum: Long,
    @SerializedName("customerNum") val customerNum: Long,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("locationX") val locationX: Float,
    @SerializedName("locationY") val locationY: Float
)
