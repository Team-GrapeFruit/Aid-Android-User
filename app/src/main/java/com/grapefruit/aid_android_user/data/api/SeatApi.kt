package com.grapefruit.aid_android_user.data.api

import com.grapefruit.aid_android_user.data.dto.SeatData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SeatApi {
    @GET("/seat/{storeId}")
    suspend fun seatList(
        @Path("storeId") storeId: Long
    ): Response<SeatData>

    @PATCH("/seat/allow/{seatId}")
    suspend fun allow(
        @Path("seatId") seatId: Long
    ): Response<Unit>

    @PATCH("/seat/cancel/{seatId}")
    suspend fun cancel(
        @Path("seatId") seatId: Long
    ): Response<Unit>
}