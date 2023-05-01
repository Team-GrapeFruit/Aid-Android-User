package com.grapefruit.aid_android_user.model.api

import com.grapefruit.aid_android_user.model.dto.SeatDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SeatService {
    @GET("/seat/{storeId}")
    fun seatList(
        @Path("storeId") storeId: Long
    ): Call<List<SeatDTO>>

    @PATCH("/seat/allow/{seatId}")
    fun allow(
        @Path("seatId") seatId: Long
    ): Call<Unit>

    @PATCH("/seat/cancel/{seatId}")
    fun cancel(
        @Path("seatId") seatId: Long
    ): Call<Unit>
}