package com.grapefruit.aid_android_user.data.api

import com.grapefruit.aid_android_user.data.dto.PurchaseData
import com.grapefruit.aid_android_user.data.dto.PurchaseSeatData
import com.grapefruit.aid_android_user.data.dto.QuantityData
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH

interface PurchaseService {
    @POST("purchase/{seatId}")
    suspend fun orderMenu(
        @Path("seatId") seatId: Long,
        @Body body: List<PurchaseSeatData>
    ): Response<Unit>

    @PATCH("purchase/{purchaseId}")
    suspend fun quantityControl(
        @Path("purchaseId") purchaseId: Long,
        @Body body: QuantityData
    ): Response<Unit>

    @DELETE("purchase/food/{purchaseId}")
    suspend fun deleteMenu(
        @Path("purchaseId") purchaseId: Long,
    ): Response<Unit>

    @GET("purchase/{seatId}")
    suspend fun addMenuList(
        @Path("seatId") seatId: Long,
    ): Response<List<PurchaseData>>
}