package com.grapefruit.aid_android_user.data.dto

data class SeatData(
    val seatId: Long,
    val seatNum: Long,
    val customerNum: Long,
    val enabled: Boolean,
    val locationX: Float,
    val locationY: Float
)
